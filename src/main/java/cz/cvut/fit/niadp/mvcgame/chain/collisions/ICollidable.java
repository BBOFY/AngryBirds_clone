package cz.cvut.fit.niadp.mvcgame.chain.collisions;

import cz.cvut.fit.niadp.mvcgame.model.Vector2;

public interface ICollidable {

    Vector2 getPos();
    byte getLayer();
    /**
     * Set mask for objects, that can change this object's properties
     */
    byte getMask();
    boolean isColliderEnabled();
    void react();

}
