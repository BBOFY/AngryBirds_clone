package cz.cvut.fit.niadp.mvcgame.chain.collisions;

import cz.cvut.fit.niadp.mvcgame.chain.collisions.AbsCollisionHandler;
import cz.cvut.fit.niadp.mvcgame.chain.collisions.colliders.ICollidable;
import cz.cvut.fit.niadp.mvcgame.chain.collisions.colliders.ICollidableAABB;
import cz.cvut.fit.niadp.mvcgame.chain.collisions.colliders.ICollidableCircle;

public class Handler_CircleAABB extends AbsCollisionHandler {
    @Override
    public boolean handleCollision(ICollidable circle, ICollidable aabb) {
        if (circle instanceof ICollidableCircle c && aabb instanceof ICollidableAABB a) {
            System.err.println("Not Implemented: Circle -- AABB");
            return false;
        }

        return handleNext(circle, aabb);
    }
}
