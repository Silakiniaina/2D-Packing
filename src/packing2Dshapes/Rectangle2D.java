
// File: src/packing2DShapes/Rectangle2D.java
package packing2Dshapes;

public class Rectangle2D extends Shape {
    private double width;
    private double height;

    public Rectangle2D(double width, double height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public double getWidth() { return rotation % Math.PI < Math.PI/2 ? width : height; }

    @Override
    public double getHeight() { return rotation % Math.PI < Math.PI/2 ? height : width; }

    @Override
    public double getArea() { return width * height; }

    @Override
    public Shape clone() { return new Rectangle2D(width, height); }
}
