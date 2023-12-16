package cz.cvut.fit.niadp.mvcgame.eventSystem;

public class EventObject_1<T> {
    private static int idCounter = 0;
    private final IListener_1<T> listener;
    public final int id;

    public EventObject_1(IListener_1<T> listener) {
        this.listener = listener;
        id = idCounter++;
    }

    public void call(T param) {
        listener.call(param);
    }

}
