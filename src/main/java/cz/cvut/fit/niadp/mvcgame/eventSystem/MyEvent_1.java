package cz.cvut.fit.niadp.mvcgame.eventSystem;

import java.util.ArrayList;
import java.util.List;

public class MyEvent_1<T> {

    private final List<MyEventObject_1<T>> subscribers;
    public MyEvent_1() {
        subscribers = new ArrayList<>();
    }
    public void addListener(MyEventObject_1<T> listener) {
        subscribers.add(listener);
    }
    public void removeListener(MyEventObject_1<T> listener) {
        for (int i = 0; i < subscribers.size(); ++i) {
            if (subscribers.get(i).id == listener.id) {
                subscribers.remove(i);
                break;
            }
        }
    }
    public void invoke(T param) {
        List<MyEventObject_1<T>> toIterate = new ArrayList<>(subscribers);
        for (MyEventObject_1<T> subscriber : toIterate) {
            subscriber.call(param);
        }
    }

}
