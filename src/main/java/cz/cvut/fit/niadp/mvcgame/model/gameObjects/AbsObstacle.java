package cz.cvut.fit.niadp.mvcgame.model.gameObjects;

import cz.cvut.fit.niadp.mvcgame.chain.collisions.ICollidable;
import cz.cvut.fit.niadp.mvcgame.visitor.gui.IGuiVisitor;
import cz.cvut.fit.niadp.mvcgame.visitor.renderer.IGameObjectsVisitor;

public abstract class AbsObstacle extends GameObject implements ICollidable {

    private boolean isColliderEnabled = true;

    @Override
    public boolean isColliderEnabled() {
        return isColliderEnabled;
    }

    @Override
    public void acceptVisitor(IGuiVisitor visitor) {

    }

    @Override
    public void acceptVisitor(IGameObjectsVisitor visitor) {
        visitor.renderVisitObstacle(this);
    }

}
