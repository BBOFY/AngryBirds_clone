package cz.cvut.fit.niadp.mvcgame.model.gameObjects;

import cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.niadp.mvcgame.model.Vector2;
import cz.cvut.fit.niadp.mvcgame.visitor.collisions.ICollidable;
import cz.cvut.fit.niadp.mvcgame.visitor.collisions.ICollidableCircle;
import cz.cvut.fit.niadp.mvcgame.visitor.collisions.ICollisionVisitor;
import cz.cvut.fit.niadp.mvcgame.visitor.gui.IGuiVisitor;
import cz.cvut.fit.niadp.mvcgame.visitor.renderer.IGameObjectsVisitor;

public class Enemy extends GameObject implements ICollidableCircle {

    private boolean isDead = false;
    private boolean colliderEnabled = true;

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
    public void acceptVisitor(IGameObjectsVisitor visitor) {
        visitor.renderVisitEnemy(this, spritePath);
    }

    @Override
    public void move(Vector2 vector) {
    }

    public boolean isDead() {
        return isDead;
    }

    @Override
    public void acceptVisitor(IGuiVisitor visitor) {
        visitor.guiVisitEnemy(this, health);
    }

    @Override
    public Vector2 getPos() {
        return position;
    }

    @Override
    public byte getLayer() {
        return MvcGameConfig.ENEMY_LAYER_BIT;
    }

    @Override
    public byte getMask() {
        return 0;
    }

    @Override
    public boolean isEnabled() {
        return colliderEnabled;
    }

    @Override
    public void react() {
        health--;
        if (health <= 0) toRemove = true;
    }

    @Override
    public double getRadius() {
        return (MvcGameConfig.ENEMY_SPRITE_WIDTH + MvcGameConfig.ENEMY_SPRITE_HEIGHT) / 4.0;
    }
}
