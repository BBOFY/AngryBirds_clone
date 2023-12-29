package cz.cvut.fit.niadp.mvcgame.memento;

import cz.cvut.fit.niadp.mvcgame.model.IGameModel;

import java.util.ArrayList;
import java.util.List;

public class CareTaker {
    private static CareTaker inst = null;
    private static int capacity = 10;
    private IGameModel model;
    private static final List<Object> mementos = new ArrayList<>();

    private CareTaker() {}

    public void createMemento() {
        if (mementos.size() >= capacity) {
            mementos.removeFirst();
        }
        mementos.addLast(model.createMemento());
    }

    public void setMemento() {
        if (!mementos.isEmpty()) {
            model.setMemento(mementos.getLast());
            mementos.removeLast();
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
