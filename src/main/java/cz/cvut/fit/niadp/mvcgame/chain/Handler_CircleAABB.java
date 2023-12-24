package cz.cvut.fit.niadp.mvcgame.chain;

import cz.cvut.fit.niadp.mvcgame.chain.collisions.ICollidable;
import cz.cvut.fit.niadp.mvcgame.chain.collisions.ICollidableAABB;
import cz.cvut.fit.niadp.mvcgame.chain.collisions.ICollidableCircle;

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
