//package cz.cvut.fit.niadp.mvcgame.model;
//
//import cz.cvut.fit.niadp.mvcgame.prototype.IClonable;
//
//public class Position implements IClonable {
//    private int x = 0;
//	private int y = 0;
//
//	public Position() {}
//
//	public Position(Position pos) {
//		this.x = pos.x;
//		this.y = pos.y;
//	}
//
//	public Position(int posX, int posY) {
//		this.x = posX;
//		this.y = posY;
//	}
//
//	public int getX() {
//		return x;
//	}
//
//    public int getY() {
//		return y;
//	}
//
//    public void setY(int y) {
//		this.y = y;
//	}
//
//    public void setX(int x) {
//		this.x = x;
//	}
//
//
//	public void add(Vector2 vector) {
//		x += vector.getX();
//		y += vector.getY();
//	}
//
//	public Position clone() {
//		return new Position(x, y);
//	}
//}