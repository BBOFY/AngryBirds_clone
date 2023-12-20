package cz.cvut.fit.niadp.mvcgame.builder;

import cz.cvut.fit.niadp.mvcgame.model.Vector2;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.Enemy;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.EnemyType;

public interface IEnemyBuilder {
    Enemy build();
    IEnemyBuilder setType(EnemyType type);
    IEnemyBuilder setPosition(Vector2 position);
    IEnemyBuilder setRotation(double rotation);
    void reset();
}
