package cz.cvut.fit.niadp.mvcgame.model;

import cz.cvut.fit.niadp.mvcgame.abstractFactory.GameObjectFactoryA;
import cz.cvut.fit.niadp.mvcgame.abstractFactory.IGameObjectFactory;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsMissile;
import cz.cvut.fit.niadp.mvcgame.strategy.MissileMovingStrategyContext;
import mockit.Mock;
import mockit.MockUp;
import mockit.Mocked;
import org.junit.Assert;
import org.junit.Test;

public class GameModelMockedTest {
    private static final Vector2 CANNON_TEST_POSITION = new Vector2(555, 666);
    private static final double INIT_TEST_ANGLE = 0;
    private static final int INIT_TEST_VELOCITY = 0;

    @Mocked
    private GameModel model;

    @Test
    public void createMissile() {
        this.generalMockSetup();
        IGameObjectFactory gameObjectsFactory = GameObjectFactoryA.getInstance();
        gameObjectsFactory.init(this.model);
        AbsMissile missile = gameObjectsFactory.createMissile(CANNON_TEST_POSITION, INIT_TEST_ANGLE, INIT_TEST_VELOCITY);
        Assert.assertEquals(CANNON_TEST_POSITION.x, missile.position.x, 0);
        Assert.assertEquals(CANNON_TEST_POSITION.y, missile.position.y, 0);
    }


    public void generalMockSetup() {
        new MockUp<GameModel>() {
            @Mock
            public MissileMovingStrategyContext getMovingStrategyContext() {
                return new MissileMovingStrategyContext();
            }
        };
    }
}
