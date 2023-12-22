package cz.cvut.fit.niadp.mvcgame.visitor.renderer;

import cz.cvut.fit.niadp.mvcgame.bridge.IGameVisuals;
import cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.niadp.mvcgame.model.Vector2;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsCannon;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsMissile;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.Enemy;

public class GameObjectsRenderer implements IGameObjectsVisitor {

    private IGameVisuals gv;

    public void setGraphicContext(IGameVisuals gv) {
        this.gv = gv;
    }


    @Override
    public void renderVisitCannon(AbsCannon cannon) {
        gv.drawImage(MvcGameConfig.CANNON_IMAGE_RESOURCE, cannon.position, cannon.getAngle(), Vector2.NEG_INF);
    }

    @Override
    public void renderVisitMissile(AbsMissile missile) {
        gv.drawImage(MvcGameConfig.MISSILE_IMAGE_RESOURCE, missile.position, 0, Vector2.NEG_INF);
    }

    @Override
    public void renderVisitEnemy(Enemy enemy, String spritePath) {
        gv.drawImage(spritePath, enemy.position, 0, Vector2.NEG_INF);
    }
}
