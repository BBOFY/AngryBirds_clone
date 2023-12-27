package cz.cvut.fit.niadp.mvcgame.model.gameObjects.A_family;

import cz.cvut.fit.niadp.mvcgame.builder.IEnemyBuilder;
import cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.niadp.mvcgame.model.Vector2;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.Enemy;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.EnemyType;
import cz.cvut.fit.niadp.mvcgame.strategy.onDeathStrategy.CreateObstacleDeathStrategy;
import cz.cvut.fit.niadp.mvcgame.strategy.onDeathStrategy.IOnDeathStrategy;
import cz.cvut.fit.niadp.mvcgame.strategy.onDeathStrategy.NothingDeathStrategy;

import java.security.InvalidParameterException;

public class EnemyBuilderA implements IEnemyBuilder {

    private Vector2 position = new Vector2(0, 0);
    private double rotation = 0;
    private int health = 0;
    private String spritePath = "";

    private static IOnDeathStrategy nothingStrategy = new NothingDeathStrategy();
    private static IOnDeathStrategy obstacleStrategy = new CreateObstacleDeathStrategy();

    private IOnDeathStrategy strategy = nothingStrategy;

    @Override
    public Enemy build() {
        return new Enemy(position, rotation, health, spritePath, strategy);
    }

    @Override
    public IEnemyBuilder setType(EnemyType type) {
        switch (type) {
            case LIGHT -> {
                spritePath = MvcGameConfig.LIGHT_ENEMY_A_SPRITE_PATH;
                health = 1;
            }
            case MEDIUM -> {
                spritePath = MvcGameConfig.MEDIUM_ENEMY_A_SPRITE_PATH;
                health = 2;
            }
            case HEAVY -> {
                spritePath = MvcGameConfig.HEAVY_ENEMY_A_SPRITE_PATH;
                health = 3;
            }
            case DEAD -> throw new InvalidParameterException("Newly created enemy should not be dead.");
        }
        return this;
    }

    @Override
    public IEnemyBuilder setPosition(Vector2 position) {
        this.position = position;
        return this;
    }

    @Override
    public IEnemyBuilder setRotation(double rotation) {
        this.rotation = rotation;
        return this;
    }

    @Override
    public IEnemyBuilder setStrategy(IOnDeathStrategy strategy) {
        this.strategy = strategy;
        return this;
    }

    @Override
    public IEnemyBuilder setNothingStrategy() {
        this.strategy = nothingStrategy;
        return this;
    }

    @Override
    public IEnemyBuilder setObstacleStrategy() {
        this.strategy = obstacleStrategy;
        return this;
    }

    @Override
    public void reset() {
        position = new Vector2(0, 0);
        rotation = 0;
        health = 0;
        spritePath = "";
        strategy = nothingStrategy;
    }

}
