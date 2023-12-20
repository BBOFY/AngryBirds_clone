package cz.cvut.fit.niadp.mvcgame.model.gameObjects.A_family;

import cz.cvut.fit.niadp.mvcgame.model.Vector2;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsEnemy;

public class EnemyA extends AbsEnemy {

    public EnemyA(Vector2 position, double rotation, int initHealth, String spritePath) {
        super(position, rotation, initHealth, spritePath);
    }
}
