package cz.cvut.fit.niadp.mvcgame.chain.cheats;

public class CheatsChecker {

    private final AbsCheatsHandler handler;

    public CheatsChecker() {
        handler = new ToggleSimpleShootCheatHandler();
    }

    public void runCheat(String msg) {
        handler.handleCheat(msg);
    }

}
