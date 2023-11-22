package cz.cvut.fit.niadp.mvcgame.model;

import cz.cvut.fit.niadp.mvcgame.prototype.IClonable;

public class Vector2 implements IClonable {
    public double x = 0;
    public double y = 0;

    public Vector2() {}

    public Vector2(Vector2 other) {
        this.x = other.x;
        this.y = other.y;
    }

    public Vector2(double x, double y) {
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
