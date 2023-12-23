package cz.cvut.fit.niadp.mvcgame.chain;

import cz.cvut.fit.niadp.mvcgame.visitor.collisions.ICollidable;
import cz.cvut.fit.niadp.mvcgame.visitor.collisions.ICollidableCircle;

public class Handler_Circles extends AbsCollisionHandler {
    @Override
    public boolean handleCollision(ICollidable a, ICollidable b) {
        if (a instanceof ICollidableCircle a1 && b instanceof ICollidableCircle b1) {
            return a.getPos().getDistanceFrom_Cubed(b.getPos()) <= (a1.getRadius() + b1.getRadius());
        }

        return handleNext(a, b);
    }
}
