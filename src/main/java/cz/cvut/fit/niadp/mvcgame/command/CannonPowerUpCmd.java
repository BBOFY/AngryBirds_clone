package cz.cvut.fit.niadp.mvcgame.command;

import cz.cvut.fit.niadp.mvcgame.model.IGameModel;

public class CannonPowerUpCmd extends AbstractGameCmd {
    public CannonPowerUpCmd(IGameModel model) {
        super(model);
    }

    @Override
    protected void execute() {
        subject.cannonPowerUp();
    }
}
