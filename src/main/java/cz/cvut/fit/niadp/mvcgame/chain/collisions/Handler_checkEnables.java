package cz.cvut.fit.niadp.mvcgame.chain.collisions;

import cz.cvut.fit.niadp.mvcgame.chain.collisions.colliders.ICollidable;

public class Handler_checkEnables extends AbsCollisionHandler {
    @Override
    public boolean handleCollision(ICollidable a, ICollidable b) {
        if (a.isColliderEnabled() && b.isColliderEnabled()) {
            return handleNext(a, b);
        }
        return false;
    }
}
