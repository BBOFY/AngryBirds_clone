package cz.cvut.fit.niadp.mvcgame.model.gameObjects;

import cz.cvut.fit.niadp.mvcgame.model.Vector2;

public abstract class GameObject {
    protected Vector2 position;

    public void move(Vector2 vector) {
        position.add(vector);
    }

    public Vector2 getPosition() {
        return position;
    }
}
