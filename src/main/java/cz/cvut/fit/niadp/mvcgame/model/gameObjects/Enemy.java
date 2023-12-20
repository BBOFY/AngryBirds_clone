package cz.cvut.fit.niadp.mvcgame.model.gameObjects;

import cz.cvut.fit.niadp.mvcgame.model.Vector2;
import cz.cvut.fit.niadp.mvcgame.visitor.collisions.ICollisionVisitable;
import cz.cvut.fit.niadp.mvcgame.visitor.collisions.ICollisionVisitor;
import cz.cvut.fit.niadp.mvcgame.visitor.renderer.IGameObjectsVisitor;

public class Enemy extends GameObject implements ICollisionVisitable {

    private double rotation;
    private int health;
    private String spritePath;

    public Enemy(Vector2 position, double rotation, int initHealth, String spritePath) {
        this.position = position;
        this.rotation = rotation;
        this.health = initHealth;
        this.spritePath = spritePath;
    }

    @Override
    public void acceptVisitor(ICollisionVisitor visitor) {

    }

    @Override
    public void acceptVisitor(IGameObjectsVisitor visitor) {
        visitor.renderVisitEnemy(this, spritePath);
    }

    @Override
    public void move(Vector2 vector) {}
}
