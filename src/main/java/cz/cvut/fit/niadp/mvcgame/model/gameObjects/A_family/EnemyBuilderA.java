package cz.cvut.fit.niadp.mvcgame.model.gameObjects.A_family;

import cz.cvut.fit.niadp.mvcgame.builder.IEnemyBuilder;
import cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.niadp.mvcgame.model.Vector2;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsEnemy;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.EnemyType;

public class EnemyBuilderA implements IEnemyBuilder {

    private Vector2 position = new Vector2(0, 0);
    private double rotation = 0;
    private int health = 0;
    private String spritePath = "";

    @Override
    public AbsEnemy build() {
        return new EnemyA(position, rotation, health, spritePath);
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
            case DEAD -> {
                spritePath = MvcGameConfig.DEAD_ENEMY_A_SPRITE_PATH;
                health = 0;
            }
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

}
