package cz.cvut.fit.niadp.mvcgame.command;

import cz.cvut.fit.niadp.mvcgame.model.IGameModel;

public class AimCannonUpCmd extends AbstractGameCmd {
    public AimCannonUpCmd(IGameModel model) {
        super(model);
    }

    @Override
    protected void execute() {
        subject.aimCannonUp();
    }
}
