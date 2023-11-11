package cz.cvut.fit.niadp.mvcgame.visitor.renderer;

public interface IGameObjectVisitable {
    void acceptVisitor(IGameObjectsVisitor visitor);
}
