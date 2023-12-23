package cz.cvut.fit.niadp.mvcgame.visitor.renderer;

import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsCannon;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsMissile;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsObstacle;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.Enemy;

public interface IGameObjectsVisitor {

    void renderVisitCannon(AbsCannon cannon);
    void renderVisitMissile(AbsMissile missile);
    // todo: visit collisions, game info, enemy

    void renderVisitEnemy(Enemy enemy, String spritePath);

    void renderVisitObstacle(AbsObstacle absObstacle);
}
