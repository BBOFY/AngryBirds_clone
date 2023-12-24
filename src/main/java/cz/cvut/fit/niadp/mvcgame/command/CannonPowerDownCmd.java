package cz.cvut.fit.niadp.mvcgame.command;

import cz.cvut.fit.niadp.mvcgame.model.IGameModel;

public class CannonPowerDownCmd extends AbstractGameCmd {
    public CannonPowerDownCmd(IGameModel model) {
        super(model);
    }

    @Override
    protected void execute() {
        subject.cannonPowerDown();
    }
}
