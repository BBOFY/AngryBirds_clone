package cz.cvut.fit.niadp.mvcgame.eventSystem;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MyEvent_1<T> {

    private final List<EventObject_1<T>> subscribers;
    public MyEvent_1() {
        subscribers = new ArrayList<>();
    }
    public void addListener(EventObject_1<T> listener) {
        subscribers.add(listener);
    }
    public void removeListener(EventObject_1<T> listener) {
        for (int i = 0; i < subscribers.size(); ++i) {
            if (subscribers.get(i).id == listener.id) {
                subscribers.remove(i);
                break;
            }
        }
    }
    public void invoke(T param) {
        List<EventObject_1<T>> toIterate = new ArrayList<>(subscribers);
        for (EventObject_1<T> subscriber : toIterate) {
            subscriber.call(param);
        }
    }

}
