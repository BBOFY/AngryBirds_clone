package cz.cvut.fit.niadp.mvcgame.strategy;

import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsMissile;
import cz.cvut.fit.niadp.mvcgame.prototype.ICloneable;

public interface IMovingStrategy extends ICloneable<IMovingStrategy> {

    void updatePosition(AbsMissile missile);

}
