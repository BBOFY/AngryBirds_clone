package cz.cvut.fit.niadp.mvcgame.command;

import cz.cvut.fit.niadp.mvcgame.model.IGameModel;

public class AimCannonDownCmd extends AbstractGameCmd {
    public AimCannonDownCmd(IGameModel model) {
        super(model);
    }

    @Override
    protected void execute() {
        subject.aimCannonDown();
    }
}
