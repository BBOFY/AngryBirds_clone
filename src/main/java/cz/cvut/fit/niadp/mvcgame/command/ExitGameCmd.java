package cz.cvut.fit.niadp.mvcgame.command;

import cz.cvut.fit.niadp.mvcgame.model.IGameModel;

public class ExitGameCmd extends AbstractGameCmd {
    public ExitGameCmd(IGameModel model) {
        super(model);
    }

    @Override
    protected void execute() {
        System.exit(0);
    }
}
