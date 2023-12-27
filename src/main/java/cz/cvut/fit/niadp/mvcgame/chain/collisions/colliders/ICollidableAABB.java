package cz.cvut.fit.niadp.mvcgame.chain.collisions.colliders;

import cz.cvut.fit.niadp.mvcgame.model.Vector2;

public interface ICollidableAABB extends ICollidable {
    Vector2 getSize();
}
