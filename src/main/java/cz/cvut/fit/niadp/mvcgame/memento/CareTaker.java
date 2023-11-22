package cz.cvut.fit.niadp.mvcgame.memento;

import cz.cvut.fit.niadp.mvcgame.model.GameModel;

import java.util.Stack;

public class CareTaker {
    private static final Stack<Object> mementos = new Stack<>();

    private CareTaker() {}

    public static void createMemento() {
        mementos.add(GameModel.getInst().createMemento());
    }

    public static void setMemento() {
        if (!mementos.empty()) {
            GameModel.getInst().setMemento(mementos.pop());
        }
    }

}
