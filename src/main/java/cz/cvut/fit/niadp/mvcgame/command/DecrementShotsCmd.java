package cz.cvut.fit.niadp.mvcgame.command;

import cz.cvut.fit.niadp.mvcgame.eventSystem.EventHolder;
import cz.cvut.fit.niadp.mvcgame.model.IGameModel;

public class DecrementShotsCmd extends AbstractGameCmd {
    public DecrementShotsCmd(IGameModel model) {
        super(model);
    }

    @Override
    protected void execute() {
        EventHolder.decShotsEvent.invoke();
    }
}
