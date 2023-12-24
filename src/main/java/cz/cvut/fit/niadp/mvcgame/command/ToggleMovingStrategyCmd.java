package cz.cvut.fit.niadp.mvcgame.command;

import cz.cvut.fit.niadp.mvcgame.model.IGameModel;

public class ToggleMovingStrategyCmd extends AbstractGameCmd {
    public ToggleMovingStrategyCmd(IGameModel model) {
        super(model);
    }

    @Override
    protected void execute() {
        subject.getMovingStrategyContext().toggleMovingStrategy();
    }
}
