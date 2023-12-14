package cz.cvut.fit.niadp.mvcgame.command;

import cz.cvut.fit.niadp.mvcgame.model.IGameModel;

public abstract class AbstractGameCmd {
    protected final IGameModel subject;
    private Object memento;

    public AbstractGameCmd(IGameModel model) {
        subject = model;
    }

    protected abstract void execute();

    public void doExecute() {
        memento = subject.createMemento();
        execute();
    }

    public void unExecute() {
        subject.setMemento(memento);
    }

}
