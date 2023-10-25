package cz.cvut.fit.niadp.mvcgame.observer.modelChangeObservers;

public interface IModelChangeObservable {
    void registerObserver(IModelChangeObserver observer, ModelChangeAspect aspect);
    void unregisterObserver(IModelChangeObserver observer, ModelChangeAspect aspect);
    void notifyObservers(ModelChangeAspect aspect);
}
