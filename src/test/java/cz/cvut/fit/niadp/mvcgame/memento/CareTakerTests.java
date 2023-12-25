package cz.cvut.fit.niadp.mvcgame.memento;

import cz.cvut.fit.niadp.mvcgame.model.GameModel;
import cz.cvut.fit.niadp.mvcgame.model.Vector2;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.A_family.CannonA;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsCannon;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsObstacle;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.Enemy;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.GameObject;
import cz.cvut.fit.niadp.mvcgame.strategy.movingStrategy.MissileMovingStrategyContext;
import mockit.Mock;
import mockit.MockUp;
import mockit.Mocked;
import org.junit.Test;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class CareTakerTests {

    @Mocked
    GameModel model;

    @Test
    public void createMementoTest() {

        CareTaker.getInstance().setModel(model);

    }

    private void generalMockSetup() {
        new MockUp<GameModel>() {

            Enemy enemy = new Enemy(new Vector2(0, 0), 0, 5, "", null);

            @Mock
            public Object createMemento() {

            }

            public void setMemento(Object memento) {

            }

            public List<? extends GameObject> getGameObjects() {
                return Stream.of(
                        Collections.singletonList(enemy)
                ).flatMap(Collection::stream).toList();
            }

            private static class Memento {
                private Enemy enemy;

            }

        };
    }

}
