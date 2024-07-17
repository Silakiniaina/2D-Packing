package packing2D;

public class Rectangle {
    private double width;
    private double height;
    private double x;
    private double y;

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
        this.x = 0;
        this.y = 0;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setPosition(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getArea() {
        return width * height;
    }
}