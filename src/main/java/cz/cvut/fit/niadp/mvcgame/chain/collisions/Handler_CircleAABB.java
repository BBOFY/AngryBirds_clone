package cz.cvut.fit.niadp.mvcgame.chain.collisions;

import cz.cvut.fit.niadp.mvcgame.chain.collisions.colliders.ICollidable;
import cz.cvut.fit.niadp.mvcgame.chain.collisions.colliders.ICollidableAABB;
import cz.cvut.fit.niadp.mvcgame.chain.collisions.colliders.ICollidableCircle;

public class Handler_CircleAABB extends AbsCollisionHandler {
    @Override
    public boolean handleCollision(ICollidable a, ICollidable b) {

        if (a instanceof ICollidableCircle circle && b instanceof ICollidableAABB aabb) {
            return checkCollision(circle, aabb);
        }
        if (b instanceof ICollidableCircle circle && a instanceof ICollidableAABB aabb) {
            return checkCollision(circle, aabb);
        }
        return handleNext(a, b);
    }

    private boolean checkCollision(ICollidableCircle circle, ICollidableAABB aabb) {

        return circle.getCenter().x + circle.getRadius() <= aabb.getPos().x + aabb.getSize().x &&
            circle.getCenter().x + circle.getRadius() >= aabb.getPos().x &&
            circle.getCenter().y + circle.getRadius() <= aabb.getPos().y + aabb.getSize().y &&
            circle.getCenter().y + circle.getRadius() >= aabb.getPos().y;

    }
}
