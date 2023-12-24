package cz.cvut.fit.niadp.mvcgame.command;

import cz.cvut.fit.niadp.mvcgame.eventSystem.EventHolder;
import cz.cvut.fit.niadp.mvcgame.model.IGameModel;

public class IncrementShotsCmd extends AbstractGameCmd {
    public IncrementShotsCmd(IGameModel model) {
        super(model);
    }

    @Override
    protected void execute() {
        EventHolder.incShotsEvent.invoke();
    }
}
