// File: src/packing2DShapes/Circle.java
package packing2Dshapes;

public class Circle extends Shape {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public double getWidth() { return 2 * radius; }

    @Override
    public double getHeight() { return 2 * radius; }

    @Override
    public double getArea() { return Math.PI * radius * radius; }

    @Override
    public Shape clone() { return new Circle(radius); }
}