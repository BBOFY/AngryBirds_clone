package cz.cvut.fit.niadp.mvcgame.chain;

import cz.cvut.fit.niadp.mvcgame.visitor.collisions.ICollidable;
import cz.cvut.fit.niadp.mvcgame.visitor.collisions.ICollidableAABB;
import cz.cvut.fit.niadp.mvcgame.visitor.collisions.ICollidableCircle;

import java.util.concurrent.ExecutionException;

public class Handler_CircleAABB extends AbsCollisionHandler {
    @Override
    public boolean handleCollision(ICollidable circle, ICollidable aabb) {
        if (circle instanceof ICollidableCircle c && aabb instanceof ICollidableAABB a) {
            throw new RuntimeException("Not Implemented: Circle -- AABB");
        }

        return handleNext(circle, aabb);
    }
}
