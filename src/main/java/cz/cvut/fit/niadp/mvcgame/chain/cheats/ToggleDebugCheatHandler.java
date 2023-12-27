package cz.cvut.fit.niadp.mvcgame.chain.cheats;

import cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.niadp.mvcgame.eventSystem.EventHolder;

import java.util.Objects;

public class ToggleDebugCheatHandler extends AbsCheatsHandler {
    @Override
    public void handleCheat(String msg) {
        if (Objects.equals(msg, MvcGameConfig.TOGGLE_DEBUG_CHEAT)) {
            EventHolder.toggleDebugEvent.invoke();
            return;
        }
        handleNext(msg);
    }
}
