package cz.cvut.fit.niadp.mvcgame.model;

import cz.cvut.fit.niadp.mvcgame.abstractFactory.GameObjectFactoryA;
import cz.cvut.fit.niadp.mvcgame.abstractFactory.IGameObjectFactory;
import cz.cvut.fit.niadp.mvcgame.builder.IEnemyBuilder;
import cz.cvut.fit.niadp.mvcgame.command.AbstractGameCmd;
import cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.niadp.mvcgame.eventSystem.EventHolder;
import cz.cvut.fit.niadp.mvcgame.eventSystem.EventObject_1;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.*;
import cz.cvut.fit.niadp.mvcgame.strategy.MissileMovingStrategyContext;
import cz.cvut.fit.niadp.mvcgame.visitor.collisions.CollisionChecker;

import java.util.*;
import java.util.stream.Stream;

public class GameModel implements IGameModel {
    private final AbsCannon cannon;

    private final CollisionChecker collisionChecker;
    private final MissileMovingStrategyContext missileMovingStrategyContext;

    private final List<AbsMissile> missiles;
    private final List<AbsObstacle> obstacles;
    private final List<Enemy> enemies;
    private static EnemyType[] enemyTypes = {EnemyType.LIGHT, EnemyType.MEDIUM, EnemyType.HEAVY};

    private final IGameObjectFactory gameObjectFactory;

    private final List<AbstractGameCmd> waitingCmds = new ArrayList<>();
    private final Stack<AbstractGameCmd> executedCmds = new Stack<>();

    public GameModel() {
        this.missiles = new ArrayList<>();
        this.gameObjectFactory = GameObjectFactoryA.getInstance();
        this.gameObjectFactory.init(this);

        this.collisionChecker = new CollisionChecker();

        this.cannon = gameObjectFactory.createCannon(MvcGameConfig.INIT_CANNON_POSITION);
        this.enemies = createEnemies(this.gameObjectFactory);
        this.obstacles = createObstacles(this.gameObjectFactory);

        this.missileMovingStrategyContext = new MissileMovingStrategyContext();

        collisionChecker.addCollider(cannon);

        EventHolder.addMissileEvent.addListener(addMissileEO);
    }

    private List<AbsObstacle> createObstacles(IGameObjectFactory factory) {
        List<AbsObstacle> newObstacles = new ArrayList<>();
        newObstacles.add(factory.createObstacles(MvcGameConfig.CANNON_UPPER_BOUND));
        newObstacles.add(factory.createObstacles(MvcGameConfig.CANNON_LOWER_BOUND));
        return newObstacles;
    }

    private List<Enemy> createEnemies(IGameObjectFactory factory) {
        IEnemyBuilder enemyBuilder = factory.createEnemyBuilder();
        List<Enemy> newEnemies = new ArrayList<>();
        Random r = new Random();

        double maxEnemyPosX = MvcGameConfig.SCREEN_WIDTH * 0.95;
        double maxEnemyPosY = MvcGameConfig.SCREEN_HEIGHT * 0.75;
        double minEnemyPosX = MvcGameConfig.SCREEN_WIDTH * 0.1;
        double minEnemyPosY = MvcGameConfig.SCREEN_HEIGHT * 0.1;

        for (int i = 0; i < MvcGameConfig.NUMBER_OF_ENEMIES; ++i) {
            Vector2 pos = new Vector2(
                    r.nextDouble(minEnemyPosX, maxEnemyPosX),
                    r.nextDouble(minEnemyPosY, maxEnemyPosY)
                    );
            enemyBuilder
                    .setPosition(pos)
                    .setRotation(r.nextDouble(2*Math.PI))
                    .setType(enemyTypes[r.nextInt(0, enemyTypes.length)]);
            Enemy enemy = enemyBuilder.build();
            newEnemies.add(enemy);
            collisionChecker.addCollider(enemy);
            enemyBuilder.reset();
        }

        return newEnemies;
    }

    @Override
    public void moveCannonUp() {
        cannon.moveUp();
        EventHolder.cannonMovedEvent.invoke(cannon);
    }

    @Override
    public void moveCannonDown() {
        cannon.moveDown();
        EventHolder.cannonMovedEvent.invoke(cannon);
    }

    @Override
    public void cannonShoot() {
        var newRockets = cannon.shoot();
        if (newRockets.isEmpty()) {
            return;
        }
        missiles.addAll(newRockets);
        newRockets.forEach(collisionChecker::addCollider);
        EventHolder.missileLaunchedEvent.invoke(missiles.get(0));
    }

    @Override
    public void aimCannonUp() {
        this.cannon.aimUp();
    }

    @Override
    public void aimCannonDown() {
        this.cannon.aimDown();
    }

    @Override
    public void cannonPowerUp() {
        this.cannon.powerUp();
    }

    @Override
    public void cannonPowerDown() {
        this.cannon.powerDown();
    }

    @Override
    public void update() {
        runCommands();

        moveMissiles();
        collisionChecker.checkCollisions();
        destroyObjects();
        EventHolder.gameObjectMovedEvent.invoke();
    }

    private void destroyObjects() {
        var toRemove = enemies.stream().filter(GameObject::needToRemove).toList();
        enemies.removeAll(toRemove);
        toRemove.forEach(collisionChecker::removeCollider);
        destroyMissiles();
    }

    private void destroyMissiles() {
        var toRemove = new ArrayList<>(missiles.stream().filter(GameObject::needToRemove).toList());
        toRemove.addAll(missiles.stream().filter(missile ->
                (missile.position.x > MvcGameConfig.SCREEN_WIDTH || missile.position.y > MvcGameConfig.SCREEN_HEIGHT
                        || missile.position.x < 0 || missile.position.y < 0
                        || missile.getAge() > MvcGameConfig.MISSILE_LIFETIME_MILLS)
        ).toList());
        missiles.removeAll(toRemove);
        toRemove.forEach(collisionChecker::removeCollider);
    }

    private void runCommands() {
        for (var c : waitingCmds) {
            c.doExecute();
            executedCmds.push(c);
        }
        waitingCmds.clear();
    }

    private void moveMissiles() {
        missiles.forEach(AbsMissile::move);
    }

    @Override
    public List<AbsMissile> getMissiles() {
        return missiles;
    }

    private final EventObject_1<AbsMissile> addMissileEO = new EventObject_1<>(this::addMissile);
    private void addMissile(AbsMissile missile) {
        missiles.add(missile);
        collisionChecker.addCollider(missile);
    }

    @Override
    public List<? extends GameObject> getGameObjects() {
        return Stream.of(
                enemies, missiles, obstacles, Collections.singletonList(cannon)
        ).flatMap(Collection::stream).toList();
    }

    @Override
    public MissileMovingStrategyContext getMovingStrategyContext() {
        return missileMovingStrategyContext;
    }

    @Override
    public void toggleShootingMode() {
        cannon.toggleShootingMode();
    }

    private static class Memento {
        private Vector2 cannonPosition;

    }

    @Override
    public Object createMemento() {
        Memento gameModelSnapshot = new Memento();
        gameModelSnapshot.cannonPosition = cannon.position.clone();
        return gameModelSnapshot;
    }

    @Override
    public void setMemento(Object memento) {
        Memento gameModelSnapshot = (Memento) memento;
        cannon.position = gameModelSnapshot.cannonPosition;
        EventHolder.gameObjectMovedEvent.invoke();
    }



    @Override
    public void registerCommand(AbstractGameCmd cmd) {
        waitingCmds.add(cmd);
    }

    @Override
    public void undoLastCommand() {
        if (executedCmds.empty()) return;
        executedCmds.pop().unExecute();
    }

    @Override
    public List<Enemy> getEnemies() {
        return enemies;
    }


}
