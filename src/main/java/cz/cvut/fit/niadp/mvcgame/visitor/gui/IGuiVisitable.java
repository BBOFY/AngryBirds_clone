package cz.cvut.fit.niadp.mvcgame.visitor.gui;

public interface IGuiVisitable {
    void acceptVisitor(IGuiVisitor visitor);
}
