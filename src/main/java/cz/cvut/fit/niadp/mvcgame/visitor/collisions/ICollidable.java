package cz.cvut.fit.niadp.mvcgame.visitor.collisions;

import cz.cvut.fit.niadp.mvcgame.model.Vector2;

public interface ICollidable {

    Vector2 getPos();
    byte getLayer();
    byte getMask();
    boolean isEnabled();
    void react();

}
