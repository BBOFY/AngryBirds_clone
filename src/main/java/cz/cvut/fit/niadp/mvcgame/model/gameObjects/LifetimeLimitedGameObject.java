package cz.cvut.fit.niadp.mvcgame.model.gameObjects;

import cz.cvut.fit.niadp.mvcgame.model.Vector2;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public abstract class LifetimeLimitedGameObject extends GameObject {

    private final LocalDateTime bornAt;

    protected LifetimeLimitedGameObject(Vector2 initPosition) {
         this.position = initPosition;
         this.bornAt = LocalDateTime.now();
    }

    public double getAge() {
        return ChronoUnit.MILLIS.between(bornAt, LocalDateTime.now());
    }

}
