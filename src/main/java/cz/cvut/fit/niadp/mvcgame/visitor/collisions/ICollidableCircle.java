package cz.cvut.fit.niadp.mvcgame.visitor.collisions;

import cz.cvut.fit.niadp.mvcgame.model.Vector2;

public interface ICollidableCircle extends ICollidable {
    double getRadius();
    Vector2 getCenter();
}