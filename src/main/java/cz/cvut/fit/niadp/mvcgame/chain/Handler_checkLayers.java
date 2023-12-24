package cz.cvut.fit.niadp.mvcgame.chain;

import cz.cvut.fit.niadp.mvcgame.chain.collisions.ICollidable;

public class Handler_checkLayers extends AbsCollisionHandler {
    @Override
    public boolean handleCollision(ICollidable a, ICollidable b) {

        if ((a.getMask() & b.getLayer()) == 0) {
            return false;
        }
        return handleNext(a, b);

    }
}
