// File: src/packing2DShapes/Shape.java
package packing2Dshapes;

public abstract class Shape {
    protected double x;
    protected double y;
    protected double rotation; // en radians

    public abstract double getWidth();
    public abstract double getHeight();
    public abstract double getArea();
    public abstract Shape clone();

    public void setPosition(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void rotate(double angle) {
        this.rotation = (this.rotation + angle) % (2 * Math.PI);
    }

    public double getX() { return x; }
    public double getY() { return y; }
    public double getRotation() { return rotation; }
}
