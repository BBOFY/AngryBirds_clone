package cz.cvut.fit.niadp.mvcgame.visitor.collisions;

public interface ICollisionVisitable {
    void acceptVisitor(ICollisionVisitor visitor);
}
