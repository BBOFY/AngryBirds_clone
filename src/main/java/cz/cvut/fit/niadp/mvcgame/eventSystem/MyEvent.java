package cz.cvut.fit.niadp.mvcgame.eventSystem;

import java.util.*;

/**
 * Event, that can store methods with no arguments
 */
public class MyEvent {
    private final List<MyEventObject> subscribers;
    public MyEvent() {
        subscribers = new ArrayList<>();
    }
    public void addListener(MyEventObject listener) {
        for (MyEventObject subscriber : subscribers) {
            if (subscriber.id == listener.id) {
                return;
            }
        }
        subscribers.add(listener);
    }
    public void removeListener(MyEventObject listener) {
        for (int i = 0; i < subscribers.size(); ++i) {
            if (subscribers.get(i).id == listener.id) {
                subscribers.remove(i);
                break;
            }
        }
    }
    public void invoke() {
        List<MyEventObject> toIterate = new ArrayList<>(subscribers);
        for (MyEventObject subscriber : toIterate) {
            subscriber.call();
        }
    }

}
