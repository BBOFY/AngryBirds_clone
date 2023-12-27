package cz.cvut.fit.niadp.mvcgame.eventSystem;

public class MyEventObject {
    private static int idCounter = 0;
    private final IListener listener;
    public final int id;

    public MyEventObject(IListener listener) {
        this.listener = listener;
        id = idCounter++;
    }

    public void call() {
        listener.call();
    }

}
