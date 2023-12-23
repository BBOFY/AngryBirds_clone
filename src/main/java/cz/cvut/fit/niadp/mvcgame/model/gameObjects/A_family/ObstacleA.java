package cz.cvut.fit.niadp.mvcgame.model.gameObjects.A_family;

import cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.niadp.mvcgame.model.Vector2;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsObstacle;
import cz.cvut.fit.niadp.mvcgame.visitor.collisions.ICollidableAABB;

public class ObstacleA extends AbsObstacle implements ICollidableAABB {

    public ObstacleA(Vector2 position) {
        this.position = position;
    }

    @Override
    public Vector2 getPos() {
        return position;
    }

    @Override
    public byte getLayer() {
        return MvcGameConfig.OBSTACLE_LAYER_BIT;
    }

    @Override
    public byte getMask() {
        return 0;
    }

    @Override
    public void react() {

    }

    @Override
    public Vector2 getSize() {
        return MvcGameConfig.OBSTACLE_SPRITE_SIZE;
    }

}
