package cz.cvut.fit.niadp.mvcgame.visitor.renderer;

import cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsCannon;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsMissile;
import cz.cvut.fit.niadp.mvcgame.nullPattern.AbstractGraphicsContextWrapper;
import cz.cvut.fit.niadp.mvcgame.nullPattern.NullGraphicsContextWrapper;

public class GameObjectsRenderer implements IGameObjectsVisitor {

    private AbstractGraphicsContextWrapper gc = NullGraphicsContextWrapper.getCurr();

    public void setGraphicContext(AbstractGraphicsContextWrapper gc) {
        this.gc = gc;
    }


    @Override
    public void renderVisitCannon(AbsCannon cannon) {
        gc.drawImage(MvcGameConfig.CANNON_IMAGE_RESOURCE, cannon.position, cannon.getAngle());
    }

    @Override
    public void renderVisitMissile(AbsMissile missile) {
        gc.drawImage(MvcGameConfig.MISSILE_IMAGE_RESOURCE, missile.position);
    }
}
