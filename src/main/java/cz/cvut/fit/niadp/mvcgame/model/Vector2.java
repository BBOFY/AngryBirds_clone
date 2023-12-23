package cz.cvut.fit.niadp.mvcgame.model;

import cz.cvut.fit.niadp.mvcgame.prototype.ICloneable;

public class Vector2 implements ICloneable<Vector2> {
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

    public static Vector2 NEG_INF = new Vector2(Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY);
    public static Vector2 ZERO = new Vector2(0, 0);

    public double getDistanceFrom_Cubed(Vector2 other) {
        return Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2);
    }
}
