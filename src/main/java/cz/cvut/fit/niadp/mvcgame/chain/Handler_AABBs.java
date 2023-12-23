package cz.cvut.fit.niadp.mvcgame.chain;

import cz.cvut.fit.niadp.mvcgame.visitor.collisions.ICollidable;
import cz.cvut.fit.niadp.mvcgame.visitor.collisions.ICollidableAABB;

public class Handler_AABBs extends AbsCollisionHandler {
    @Override
    public boolean handleCollision(ICollidable a, ICollidable b) {
        if (a instanceof ICollidableAABB a1 && b instanceof ICollidableAABB b1) {

            return a1.getPos().x < b1.getPos().x + b1.getSize().x &&
                    a1.getPos().x + a1.getSize().x > b1.getPos().x &&
                    a1.getPos().y < b1.getPos().y + b1.getSize().y &&
                    a1.getPos().y + a1.getSize().y > b1.getPos().y;

        }

        return handleNext(a, b);
    }
}
