package cz.cvut.fit.niadp.mvcgame.command;

import cz.cvut.fit.niadp.mvcgame.model.IGameModel;

public abstract class AbstractGameCommand {

    private IGameModel subject;
    private Object memento;
    protected abstract void execute();

    public void doExecute() {
        memento = subject.createMemento();
        execute();
    }

    public void unExecute() {
        subject.setMemento(memento);
    }

}
