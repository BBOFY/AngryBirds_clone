package cz.cvut.fit.niadp.mvcgame.chain.collisions;

import cz.cvut.fit.niadp.mvcgame.chain.collisions.colliders.ICollidable;
import cz.cvut.fit.niadp.mvcgame.chain.collisions.colliders.ICollidableCircle;

public class Handler_Circles extends AbsCollisionHandler {
    @Override
    public boolean handleCollision(ICollidable a, ICollidable b) {
        if (a instanceof ICollidableCircle a1 && b instanceof ICollidableCircle b1) {
            return a1.getPos().getDistanceFrom_Cubed(b1.getPos()) <= (Math.pow(a1.getRadius() + b1.getRadius(), 2));
        }

        return handleNext(a, b);
    }
}
