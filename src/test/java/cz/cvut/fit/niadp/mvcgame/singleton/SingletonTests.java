package cz.cvut.fit.niadp.mvcgame.singleton;

import cz.cvut.fit.niadp.mvcgame.abstractFactory.GameObjectFactoryA;
import cz.cvut.fit.niadp.mvcgame.abstractFactory.IGameObjectFactory;
import org.junit.Assert;
import org.junit.Test;

public class SingletonTests {

    @Test
    public void singletonTest() {
        IGameObjectFactory instance1 = GameObjectFactoryA.getInstance();
        IGameObjectFactory instance2 = GameObjectFactoryA.getInstance();
        Assert.assertEquals(instance1.hashCode(), instance2.hashCode());
    }

}
