package cz.cvut.fit.niadp.mvcgame.command;

import cz.cvut.fit.niadp.mvcgame.model.IGameModel;

public class MoveCannonUpCmd extends AbstractGameCmd {

    public MoveCannonUpCmd(IGameModel model) {
        super(model);
    }

    @Override
    protected void execute() {
        subject.moveCannonUp();
    }
}
