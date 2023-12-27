package cz.cvut.fit.niadp.mvcgame.model.gameObjects;

import cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.niadp.mvcgame.model.Vector2;
import cz.cvut.fit.niadp.mvcgame.chain.collisions.colliders.ICollidableCircle;
import cz.cvut.fit.niadp.mvcgame.prototype.ICloneable;
import cz.cvut.fit.niadp.mvcgame.strategy.onDeathStrategy.IOnDeathStrategy;
import cz.cvut.fit.niadp.mvcgame.visitor.gui.IGuiVisitor;
import cz.cvut.fit.niadp.mvcgame.visitor.renderer.IGameObjectsVisitor;

public class Enemy extends GameObject implements ICollidableCircle, ICloneable<Enemy> {
    private boolean isColliderEnabled = true;

    private IOnDeathStrategy onDeathStrategy;

    private double rotation;
    private Vector2 colliderCenter;
    private double colliderRadius;
    private int health;
    private String spritePath;

    public Enemy(Vector2 position, double rotation, int initHealth, String spritePath, IOnDeathStrategy strategy) {
        this.position = position;
        this.rotation = rotation;
        this.health = initHealth;
        this.spritePath = spritePath;
        this.onDeathStrategy = strategy;
        colliderCenter = new Vector2(
                position.x + MvcGameConfig.ENEMY_SPRITE_WIDTH / 2.0,
                position.y + MvcGameConfig.ENEMY_SPRITE_HEIGHT / 2.0
        );
        colliderRadius = (MvcGameConfig.ENEMY_SPRITE_WIDTH + MvcGameConfig.ENEMY_SPRITE_HEIGHT) / 4.0;
    }

    @Override
    public void acceptVisitor(IGameObjectsVisitor visitor) {
        visitor.renderVisitEnemy(this, spritePath);
    }

    @Override
    public void move(Vector2 vector) {
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
        return MvcGameConfig.MISSILE_LAYER_BIT;
    }

    @Override
    public boolean isColliderEnabled() {
        return isColliderEnabled;
    }

    @Override
    public void react() {
        health--;
        if (health <= 0) {
            toRemove = true;
            onDeathStrategy.doSomething(this);
        }
    }

    @Override
    public double getRadius() {
        return colliderRadius;
    }

    @Override
    public Vector2 getCenter() {
        return colliderCenter;
    }

    @Override
    public Enemy clone() {
        Enemy newEnemy = new Enemy(position.clone(), rotation, health, spritePath, onDeathStrategy);
        newEnemy.toRemove = toRemove;
        newEnemy.colliderCenter = colliderCenter.clone();
        newEnemy.isColliderEnabled = isColliderEnabled;
        newEnemy.colliderRadius = colliderRadius;
        return newEnemy;
    }
}
