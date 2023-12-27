package cz.cvut.fit.niadp.mvcgame.chain.cheats;

public abstract class AbsCheatsHandler {

    private AbsCheatsHandler next;

    public AbsCheatsHandler setNext(AbsCheatsHandler handler) {
        next = handler;
        return next;
    }

    public abstract void handleCheat(String msg);

    protected void handleNext(String msg) {
        if (next == null) return;
        next.handleCheat(msg);
    }

}
