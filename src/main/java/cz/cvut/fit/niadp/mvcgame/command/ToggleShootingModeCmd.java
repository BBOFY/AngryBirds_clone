package cz.cvut.fit.niadp.mvcgame.command;

import cz.cvut.fit.niadp.mvcgame.model.IGameModel;

public class ToggleShootingModeCmd extends AbstractGameCmd {
    public ToggleShootingModeCmd(IGameModel model) {
        super(model);
    }

    @Override
    protected void execute() {
        subject.toggleShootingMode();
    }
}
