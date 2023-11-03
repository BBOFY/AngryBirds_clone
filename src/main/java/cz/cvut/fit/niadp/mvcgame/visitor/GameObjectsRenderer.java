package cz.cvut.fit.niadp.mvcgame.visitor;

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
    public void visitCannon(AbsCannon cannon) {
        gc.drawImage(MvcGameConfig.CANNON_IMAGE_RESOURCE, cannon.getPos());
    }

    @Override
    public void visitMissile(AbsMissile missile) {
        gc.drawImage(MvcGameConfig.MISSILE_IMAGE_RESOURCE, missile.getPos());
    }
}
