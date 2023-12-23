package cz.cvut.fit.niadp.mvcgame.chain;

import cz.cvut.fit.niadp.mvcgame.visitor.collisions.ICollidable;

public class Handler_checkEnables extends AbsCollisionHandler {
    @Override
    public boolean handleCollision(ICollidable a, ICollidable b) {
        if (a.isEnabled() && b.isEnabled()) {
            return handleNext(a, b);
        }
        return false;
    }
}
