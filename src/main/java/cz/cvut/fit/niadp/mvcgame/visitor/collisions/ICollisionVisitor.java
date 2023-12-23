package cz.cvut.fit.niadp.mvcgame.visitor.collisions;

public interface ICollisionVisitor {
    boolean checkCollision(ICollidable a, ICollidable b);
}
