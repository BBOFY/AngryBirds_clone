package cz.cvut.fit.niadp.mvcgame.chain.cheats;

import cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig;

import java.util.Objects;

public class ToggleSimpleShootCheatHandler extends AbsCheatsHandler {
    @Override
    public void handleCheat(String msg) {
        if (Objects.equals(msg, MvcGameConfig.TOGGLE_SIMPLE_SHOOT_CHEAT)) {

            return;
        }
        handleNext(msg);
    }
}
