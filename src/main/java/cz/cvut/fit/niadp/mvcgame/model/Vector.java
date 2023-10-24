package cz.cvut.fit.niadp.mvcgame.model;

public class Vector {
    private int dX = 0;
    private int dY = 0;

    public Vector() {
    }

    public Vector(int posX, int posY) {
        this.dX = posX;
        this.dY = posY;
    }

    public int getX() {
        return dX;
    }

    public int getY() {
        return dY;
    }

    public void setX(int x) {
        this.dX = x;
    }

    public void setY(int y) {
        this.dY = y;
    }
}
