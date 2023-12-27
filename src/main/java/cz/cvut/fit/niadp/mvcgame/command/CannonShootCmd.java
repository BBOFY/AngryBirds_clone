package cz.cvut.fit.niadp.mvcgame.command;

import cz.cvut.fit.niadp.mvcgame.model.IGameModel;

public class CannonShootCmd extends AbstractGameCmd {
    public CannonShootCmd(IGameModel model) {
        super(model);
    }

    @Override
    protected void execute() {
        if (subject.isInDebugMode() || subject.getMissiles().isEmpty()) {
            subject.cannonShoot();
        }
    }
}
