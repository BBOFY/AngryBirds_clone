package cz.cvut.fit.niadp.mvcgame.eventSystem;

/**
 * Interface for method with 1 argument and no return value to be used as listener in event.
 */
public interface IListener_1<T> {
    void call(T param);
}
