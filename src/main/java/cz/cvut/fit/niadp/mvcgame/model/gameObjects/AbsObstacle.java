package cz.cvut.fit.niadp.mvcgame.model.gameObjects;

import cz.cvut.fit.niadp.mvcgame.chain.collisions.colliders.ICollidable;
import cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.niadp.mvcgame.model.Vector2;
import cz.cvut.fit.niadp.mvcgame.prototype.ICloneable;
import cz.cvut.fit.niadp.mvcgame.visitor.gui.IGuiVisitor;
import cz.cvut.fit.niadp.mvcgame.visitor.renderer.IGameObjectsVisitor;

public abstract class AbsObstacle extends GameObject implements ICollidable, ICloneable<AbsObstacle> {

    protected boolean isColliderEnabled = true;
    protected Vector2 velocity;

    protected AbsObstacle(Vector2 position, Vector2 velocity) {
        this.position = position;
        this.velocity = velocity;
    }

    @Override
    public boolean isColliderEnabled() {
        return isColliderEnabled;
    }

    @Override
    public void acceptVisitor(IGuiVisitor visitor) {}

    @Override
    public void move(Vector2 vector) {

        if (position.x < 0 || position.x >= MvcGameConfig.SCREEN_WIDTH) {
            velocity.x *= -1;
        }
        if (position.y < 0 || position.y >= MvcGameConfig.SCREEN_HEIGHT) {
            velocity.y *= -1;
        }

        position.add(velocity);
    }

    @Override
    public void acceptVisitor(IGameObjectsVisitor visitor) {
        visitor.renderVisitObstacle(this);
    }

    @Override
    public abstract AbsObstacle clone();
}
