package cz.cvut.fit.niadp.mvcgame.model.gameObjects;

import cz.cvut.fit.niadp.mvcgame.model.Vector2;
import cz.cvut.fit.niadp.mvcgame.visitor.renderer.IGameObjectVisitable;

public abstract class GameObject implements IGameObjectVisitable {
    public Vector2 position;

    public void move(Vector2 vector) {
        position.add(vector);
    }
}
