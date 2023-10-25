package cz.cvut.fit.niadp.mvcgame.eventSystem;

import java.util.HashSet;
import java.util.Set;

/**
 * Event, that can store methods with no arguments
 */
public class MyEvent {
    private final Set<IListener> subscribers;
    public MyEvent() {
        subscribers = new HashSet<>();
    }
    public void addListener(IListener listener) {
        subscribers.add(listener);
    }
    public void removeListener(IListener listener) {
        subscribers.remove(listener);
    }
    public void invoke() {
        subscribers.forEach(IListener::call);
    }

}
