package cz.cvut.fit.niadp.mvcgame.eventSystem;

/**
 * Interface for method with no arguments and no return value to be used as listener in event.
 */
@FunctionalInterface
public interface IListener {
    void call();
}

