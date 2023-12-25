package cz.cvut.fit.niadp.mvcgame.model;
import org.junit.Assert;
import org.junit.Test;

import java.util.stream.IntStream;

public class GameModelMissilesTest {
    private static final int ITERATION_START_CONST = 1;
    private static final int MISSILE_COUNT = 50;
    private static final int MOVE_COUNT = 100000;
    private static final int EXPECTED_MISSILES_COUNT = 0;

    @Test
    public void moveMissilesTest() {
        GameModel model = new GameModel();
        IntStream.rangeClosed(ITERATION_START_CONST, MISSILE_COUNT).forEach(i->model.cannonShoot());
        Assert.assertEquals(MISSILE_COUNT, model.getMissiles().size());
        IntStream.rangeClosed(ITERATION_START_CONST, MOVE_COUNT).forEach(i-> {model.update();});
        Assert.assertEquals(EXPECTED_MISSILES_COUNT, model.getMissiles().size());
    }
}



