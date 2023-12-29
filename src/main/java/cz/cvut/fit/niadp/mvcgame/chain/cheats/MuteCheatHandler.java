package cz.cvut.fit.niadp.mvcgame.chain.cheats;

import cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.niadp.mvcgame.eventSystem.EventHolder;

import java.util.Objects;

public class MuteCheatHandler extends AbsCheatsHandler {
    @Override
    public void handleCheat(String msg) {
        if (Objects.equals(msg, MvcGameConfig.MUTE_CHEAT)) {
            EventHolder.toggleMuteEvent.invoke();
            return;
        }
        handleNext(msg);
    }
}
