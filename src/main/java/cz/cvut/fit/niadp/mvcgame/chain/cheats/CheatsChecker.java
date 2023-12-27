package cz.cvut.fit.niadp.mvcgame.chain.cheats;

import cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig;

import java.util.List;
import java.util.Objects;

public class CheatsChecker {

    private final AbsCheatsHandler handler;
    private String cheat = "";

    public CheatsChecker() {
        handler = new ToggleDebugCheatHandler();
    }
    private void runCheat() {
        handler.handleCheat(cheat);
        System.out.println("Entered cheat: " + cheat);
        cheat = "";
    }

    /**
     * Returns false if parsing of cheat stopped
     * Returns true, if keys were added to string successfully and cheat is not written yet
     */
    public boolean addKeysToCheat(List<String> keys) {
        for (String k : keys) {
            if (keys.isEmpty()) return true;
            if (Objects.equals(k, MvcGameConfig.ENTER_CHEAT_KEY)) {
                runCheat();
                return false;
            }
            if (Objects.equals(k, "SPACE")) {
                cheat += " ";
                continue;
            }
            if (k.length() == 1 && Character.isLetter(k.charAt(0))) {
                cheat += k.toLowerCase().charAt(0);
                continue;
            }
            cheat = "";
            return false;
        }
        return true;
    }

}
