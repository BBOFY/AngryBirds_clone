package cz.cvut.fit.niadp.mvcgame.eventSystem;

import java.util.*;

/**
 * Event, that can store methods with no arguments
 */
public class MyEvent {
    private final List<EventObject> subscribers;
    public MyEvent() {
        subscribers = new ArrayList<>();
    }
    public void addListener(EventObject listener) {
        for (EventObject subscriber : subscribers) {
            if (subscriber.id == listener.id) {
                return;
            }
        }
        subscribers.add(listener);
    }
    public void removeListener(EventObject listener) {
        for (int i = 0; i < subscribers.size(); ++i) {
            if (subscribers.get(i).id == listener.id) {
                subscribers.remove(i);
                break;
            }
        }
    }
    public void invoke() {
        List<EventObject> toIterate = new ArrayList<>(subscribers);
        for (EventObject subscriber : toIterate) {
            subscriber.call();
        }
    }

}
