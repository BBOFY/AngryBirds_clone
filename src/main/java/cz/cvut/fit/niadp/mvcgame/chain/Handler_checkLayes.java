package cz.cvut.fit.niadp.mvcgame.chain;

import cz.cvut.fit.niadp.mvcgame.visitor.collisions.ICollidable;

public class Handler_checkLayes extends AbsCollisionHandler {
    @Override
    public boolean handleCollision(ICollidable a, ICollidable b) {

        if ((a.getMask() & b.getLayer()) == 0) {
            return false;
        }
        return handleNext(a, b);

    }
}
