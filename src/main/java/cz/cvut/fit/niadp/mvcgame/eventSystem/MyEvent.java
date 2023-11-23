package cz.cvut.fit.niadp.mvcgame.eventSystem;

import java.util.*;

/**
 * Event, that can store methods with no arguments
 */
public class MyEvent {
    private final List<IListener> subscribers;
    public MyEvent() {
        subscribers = new ArrayList<>();
    }
    public void addListener(IListener listener) {
        System.out.println(listener);
        for (IListener subscriber : subscribers) {
            if (subscriber == listener) {
                return;
            }
        }
        subscribers.add(listener);
    }
    public void removeListener(IListener listener) {
        System.out.println(listener);
        for (int i = 0; i < subscribers.size(); ++i) {
            if (subscribers.get(i) == listener) {
                subscribers.remove(i);
                break;
            }
        }
    }
    public void invoke() {
        subscribers.forEach(IListener::call);
    }

}
