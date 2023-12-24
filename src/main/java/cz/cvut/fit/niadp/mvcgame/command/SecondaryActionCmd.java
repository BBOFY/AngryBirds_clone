package cz.cvut.fit.niadp.mvcgame.command;

import cz.cvut.fit.niadp.mvcgame.eventSystem.EventHolder;
import cz.cvut.fit.niadp.mvcgame.model.IGameModel;

public class SecondaryActionCmd extends AbstractGameCmd {
    public SecondaryActionCmd(IGameModel model) {
        super(model);
    }

    @Override
    protected void execute() {
        EventHolder.secondaryActionEvent.invoke();
    }
}
