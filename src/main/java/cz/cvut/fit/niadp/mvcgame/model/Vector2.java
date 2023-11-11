package cz.cvut.fit.niadp.mvcgame.model;

import cz.cvut.fit.niadp.mvcgame.prototype.IClonable;

public class Vector2 implements IClonable {
    public int x = 0;
    public int y = 0;

    public Vector2() {}

    public Vector2(Vector2 other) {
        this.x = other.x;
        this.y = other.y;
    }

    public Vector2(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void add(Vector2 other) {
        x += other.x;
        y += other.y;
    }

    @Override
    public Vector2 clone() {
        return new Vector2(x, y);
    }
}
