package cz.cvut.fit.niadp.mvcgame.chain.collisions;

import cz.cvut.fit.niadp.mvcgame.model.Vector2;

public interface ICollidableCircle extends ICollidable {
    double getRadius();
    Vector2 getCenter();
}
