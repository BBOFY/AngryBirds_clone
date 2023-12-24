package cz.cvut.fit.niadp.mvcgame.command;

import cz.cvut.fit.niadp.mvcgame.model.IGameModel;

public class UndoLastCommandCmd extends AbstractGameCmd {
    public UndoLastCommandCmd(IGameModel model) {
        super(model);
    }

    @Override
    protected void execute() {
        subject.undoLastCommand();
    }

    @Override
    public void doExecute() {
        execute();
    }
}
