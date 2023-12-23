package cz.cvut.fit.niadp.mvcgame.chain;

import cz.cvut.fit.niadp.mvcgame.visitor.collisions.ICollidable;

public abstract class AbsCollisionHandler {

    private AbsCollisionHandler next;

    public AbsCollisionHandler setNext(AbsCollisionHandler handler) {
        next = handler;
        return next;
    }

    public abstract boolean handleCollision(ICollidable a, ICollidable b);

    protected boolean handleNext(ICollidable a, ICollidable b) {
        if (next != null) {
            return next.handleCollision(a, b);
        }
        return false;
    }
}
