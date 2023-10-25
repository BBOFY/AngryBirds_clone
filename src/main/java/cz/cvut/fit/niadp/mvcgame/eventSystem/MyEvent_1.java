package cz.cvut.fit.niadp.mvcgame.eventSystem;

import java.util.HashSet;
import java.util.Set;

public class MyEvent_1<T> {

    private final Set<IListener_1<T>> subscribers;
    public MyEvent_1() {
        subscribers = new HashSet<>();
    }
    public void addListener(IListener_1<T> listener) {
        subscribers.add(listener);
    }
    public void removeListener(IListener_1<T> listener) {
        subscribers.remove(listener);
    }
    public void invoke(T param) {
        subscribers.forEach(subscriber -> subscriber.call(param));
    }

}
