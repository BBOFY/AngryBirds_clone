package cz.cvut.fit.niadp.mvcgame.abstractFactory;

import cz.cvut.fit.niadp.mvcgame.model.Vector2;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsCannon;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsMissile;

public interface IGameObjectFactory {
    AbsCannon createCannon(Vector2 position);
    AbsMissile createMissile(Vector2 position, double angle, int velocity);

    // TODO: other game object, albeit this is not very "game dev" style

}
