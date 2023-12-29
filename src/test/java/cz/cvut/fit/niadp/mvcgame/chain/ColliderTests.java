package cz.cvut.fit.niadp.mvcgame.chain;

import cz.cvut.fit.niadp.mvcgame.chain.collisions.CollisionChecker;
import cz.cvut.fit.niadp.mvcgame.model.Vector2;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.A_family.EnemyBuilderA;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.A_family.MissileA;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsMissile;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.Enemy;
import mockit.Mock;
import mockit.MockUp;
import mockit.Mocked;
import org.junit.Assert;
import org.junit.Test;

public class ColliderTests {

    CollisionChecker checker = new CollisionChecker();
    EnemyBuilderA builder = new EnemyBuilderA();

    @Mocked
    AbsMissile missile1 = new MissileA(new Vector2(100, 100), 0, 0, null);
    @Mocked
    AbsMissile missile2 = new MissileA(new Vector2(200, 200), 0, 0, null);
    @Mocked
    AbsMissile missile3 = new MissileA(new Vector2(200, 200), 0, 0, null);

    int collisionNumber = 0;

    @Test
    public void testCollisions() {
        generalMockSetup();
        Enemy enemy = builder.setPosition(new Vector2(200, 200)).build();

        collisionNumber = 0;
        checker.addCollider(enemy);
        checker.addCollider(missile2);
        checker.checkCollisions();
        Assert.assertEquals(1, collisionNumber);

        collisionNumber = 0;
        checker.removeCollider(missile2);
        checker.addCollider(missile1);
        checker.checkCollisions();
        Assert.assertEquals(0, collisionNumber);

        collisionNumber = 0;
        checker.removeCollider(missile1);
        checker.addCollider(missile2);
        checker.addCollider(missile3);
        checker.checkCollisions();
        Assert.assertEquals(2, collisionNumber);
    }

    private void generalMockSetup() {
        new MockUp<Enemy>() {
            @Mock
            public void react() {
                collisionNumber++;
            }

        };

        new MockUp<MissileA>() {
            @Mock
            public void react() {
            }

        };
    }


}
