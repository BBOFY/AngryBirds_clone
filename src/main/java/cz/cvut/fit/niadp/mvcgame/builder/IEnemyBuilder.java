package cz.cvut.fit.niadp.mvcgame.builder;

import cz.cvut.fit.niadp.mvcgame.model.Vector2;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsEnemy;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.EnemyType;

public interface IEnemyBuilder {
    AbsEnemy build();
    IEnemyBuilder setType(EnemyType type);
    IEnemyBuilder setPosition(Vector2 position);
    IEnemyBuilder setRotation(double rotation);
}
