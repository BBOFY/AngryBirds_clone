package cz.cvut.fit.niadp.mvcgame.abstractFactory;

import org.junit.Assert;
import org.junit.Test;

public class GameObjectsFactoryATest {

    @Test
    public void singletonTest() {
        IGameObjectFactory instance1 = GameObjectFactoryA.getInstance();
        IGameObjectFactory instance2 = GameObjectFactoryA.getInstance();
        Assert.assertEquals(instance1.hashCode(), instance2.hashCode());
    }

}
