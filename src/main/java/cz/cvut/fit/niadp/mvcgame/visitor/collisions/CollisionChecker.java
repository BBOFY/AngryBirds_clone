package cz.cvut.fit.niadp.mvcgame.visitor.collisions;

import cz.cvut.fit.niadp.mvcgame.chain.*;

import java.util.ArrayList;
import java.util.List;

public class CollisionChecker {

    private final AbsCollisionHandler chainChecker;
    private final List<ICollidable> colliders = new ArrayList<>();

    public CollisionChecker() {
        chainChecker = new Handler_checkEnables();
        chainChecker.setNext(new Handler_checkLayes())
                .setNext(new Handler_Circles())
                .setNext(new Handler_CircleAABB())
                .setNext(new Handler_AABBs());
    }

    public void addCollider(ICollidable collider) {
        colliders.add(collider);
    }

    public void checkCollisions() {

        for (var c : colliders) {
            if (!c.isEnabled()) continue;
            for (var other : colliders) {
                if (c == other) continue;
                if (checkCollision(c, other)) c.react();

            }
        }

    }

    public boolean checkCollision(ICollidable a, ICollidable b) {
        return chainChecker.handleCollision(a, b);
    }
}
