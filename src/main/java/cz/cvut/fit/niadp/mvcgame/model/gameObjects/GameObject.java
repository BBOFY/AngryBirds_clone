package cz.cvut.fit.niadp.mvcgame.model.gameObjects;

import cz.cvut.fit.niadp.mvcgame.model.Vector2;
import cz.cvut.fit.niadp.mvcgame.visitor.IGameObjectVisitable;

public abstract class GameObject implements IGameObjectVisitable {
    protected Vector2 position;

    public void move(Vector2 vector) {
        position.add(vector);
    }

    public Vector2 getPos() {
        return position;
    }
}
