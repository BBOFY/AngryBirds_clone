package cz.cvut.fit.niadp.mvcgame.visitor;

public interface IGameObjectVisitable {
    void acceptVisitor(IGameObjectsVisitor visitor);
}
