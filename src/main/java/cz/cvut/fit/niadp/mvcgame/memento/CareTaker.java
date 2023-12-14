package cz.cvut.fit.niadp.mvcgame.memento;

import cz.cvut.fit.niadp.mvcgame.model.IGameModel;

import java.util.Stack;

public class CareTaker {
    private static CareTaker inst = null;
    private IGameModel model;
    private static final Stack<Object> mementos = new Stack<>();

    private CareTaker() {}

    public void createMemento() {
        mementos.add(model.createMemento());
    }

    public void setMemento() {
        if (!mementos.empty()) {
            model.setMemento(mementos.pop());
        }
    }

    public static CareTaker getInstance() {
        if (inst == null) {
            inst = new CareTaker();
        }
        return inst;
    }

    public void setModel (IGameModel model) {
        this.model = model;
    }

}
