package cz.cvut.fit.niadp.mvcgame.model.gameObjects;

import cz.cvut.fit.niadp.mvcgame.model.Vector2;
import cz.cvut.fit.niadp.mvcgame.visitor.gui.IGuiVisitable;
import cz.cvut.fit.niadp.mvcgame.visitor.renderer.IGameObjectVisitable;

public abstract class GameObject implements IGameObjectVisitable, IGuiVisitable {
    public Vector2 position;
    protected boolean toRemove = false;
    public void move(Vector2 vector) {
        position.add(vector);
    }
    public boolean needToRemove() {
        return toRemove;
    }
}
