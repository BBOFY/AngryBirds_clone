package cz.cvut.fit.niadp.mvcgame.model;

import cz.cvut.fit.niadp.mvcgame.abstractFactory.GameObjectFactoryA;
import cz.cvut.fit.niadp.mvcgame.abstractFactory.IGameObjectFactory;
import cz.cvut.fit.niadp.mvcgame.builder.IEnemyBuilder;
import cz.cvut.fit.niadp.mvcgame.command.AbstractGameCmd;
import cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.niadp.mvcgame.eventSystem.EventHolder;
import cz.cvut.fit.niadp.mvcgame.eventSystem.MyEventObject;
import cz.cvut.fit.niadp.mvcgame.eventSystem.MyEventObject_1;
import cz.cvut.fit.niadp.mvcgame.memento.CareTaker;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.*;
import cz.cvut.fit.niadp.mvcgame.strategy.movingStrategy.MissileMovingStrategyContext;
import cz.cvut.fit.niadp.mvcgame.chain.collisions.CollisionChecker;

import java.util.*;
import java.util.stream.Stream;

public class GameModel implements IGameModel {
    private AbsCannon cannon;

    private boolean debugMode = false;

    private final CollisionChecker collisionChecker;
    private final MissileMovingStrategyContext missileMovingStrategyContext;

    private final List<AbsMissile> missiles;
    private List<AbsObstacle> obstacles;
    private List<Enemy> enemies;
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
        EventHolder.addObstacleEvent.addListener(addObstacleEO);
        EventHolder.toggleDebugEvent.addListener(toggleDebugEO);

        CareTaker.getInstance().setModel(this);
    }

    private List<AbsObstacle> createObstacles(IGameObjectFactory factory) {
        List<AbsObstacle> newObstacles = new ArrayList<>();
        newObstacles.add(factory.createObstacle(MvcGameConfig.CANNON_UPPER_BOUND, Vector2.ZERO));
        newObstacles.add(factory.createObstacle(MvcGameConfig.CANNON_LOWER_BOUND, Vector2.ZERO));

//        newObstacles.addAll(addMovableObstacles(
//                new Vector2(100, 0),
//                new Vector2(1, 3),
//                new Vector2(0, 8))
//        );

        newObstacles.forEach(collisionChecker::addCollider);
        return newObstacles;
    }

    private List<AbsObstacle> addMovableObstacles(Vector2 pos, Vector2 size, Vector2 velocity) {
        List<AbsObstacle> newObstacles = new ArrayList<>();

        for (int x = 0; x < size.x; ++x) {
            for (int y = 0; y < size.y; ++y) {
                newObstacles.add(gameObjectFactory.createObstacle(
                        new Vector2(
                                pos.x + MvcGameConfig.OBSTACLE_SPRITE_SIZE.x * x,
                                pos.y + MvcGameConfig.OBSTACLE_SPRITE_SIZE.y * y
                        ),
                        velocity.clone()
                ));
            }
        }

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

            if (r.nextBoolean()) {
                enemyBuilder.setNothingStrategy();
            }
            else {
                enemyBuilder.setObstacleStrategy();
            }

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
    public AbsCannon getCannon() {
        return cannon;
    }

    @Override
    public void update() {
        collisionChecker.checkCollisions();
        destroyObjects();
        moveObstacles();
        moveMissiles();
        runCommands();
        EventHolder.gameObjectMovedEvent.invoke();
    }

    private void moveObstacles() {
        obstacles.forEach(o -> o.move(null));
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

    private void moveMissiles() {
        missiles.forEach(AbsMissile::move);
    }

    @Override
    public List<AbsMissile> getMissiles() {
        return missiles;
    }

    private final MyEventObject_1<AbsMissile> addMissileEO = new MyEventObject_1<>(this::addMissile);
    private void addMissile(AbsMissile missile) {
        missiles.add(missile);
        collisionChecker.addCollider(missile);
    }

    private final MyEventObject_1<AbsObstacle> addObstacleEO = new MyEventObject_1<>(this::addObstacle);
    private void addObstacle(AbsObstacle obstacle) {
        obstacles.add(obstacle);
        collisionChecker.addCollider(obstacle);
    }

    private final MyEventObject toggleDebugEO = new MyEventObject(this::toggleDebug);
    private void toggleDebug() {
        debugMode = !debugMode;
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
        private AbsCannon cannon;
        private List<Enemy> enemies;
        private List<AbsObstacle> obstacles;

    }

    @Override
    public Object createMemento() {
        Memento gameModelSnapshot = new Memento();
        gameModelSnapshot.cannon = cannon.clone();

        List<Enemy> newEnemies = new ArrayList<>();
        enemies.forEach(e -> newEnemies.add(e.clone()));
        gameModelSnapshot.enemies = newEnemies;

        List<AbsObstacle> newObstacles = new ArrayList<>();
        obstacles.forEach(o -> newObstacles.add(o.clone()));
        gameModelSnapshot.obstacles = newObstacles;

        return gameModelSnapshot;
    }

    @Override
    public void setMemento(Object memento) {
        Memento gameModelSnapshot = (Memento) memento;
        collisionChecker.removeCollider(cannon);
        enemies.forEach(collisionChecker::removeCollider);
        obstacles.forEach(collisionChecker::removeCollider);

        cannon = gameModelSnapshot.cannon;
        enemies = gameModelSnapshot.enemies;
        obstacles = gameModelSnapshot.obstacles;

        collisionChecker.addCollider(cannon);
        enemies.forEach(collisionChecker::addCollider);
        obstacles.forEach(collisionChecker::addCollider);
    }

    @Override
    public void registerCommand(AbstractGameCmd cmd) {
        waitingCmds.add(cmd);
    }

    private void runCommands() {

        for (var c : waitingCmds) {
            c.doExecute();
            executedCmds.push(c);
        }
        waitingCmds.clear();
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

    @Override
    public boolean isInDebugMode() {
        return debugMode;
    }

}
