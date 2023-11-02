package cz.cvut.fit.niadp.mvcgame.model.gameObjects;

public abstract class AbsCannon extends GameObject {

    public abstract void moveUp();

    public abstract void moveDown();

    public abstract AbsMissile shoot();

}
