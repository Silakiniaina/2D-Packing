
package packing2Dshapes;

public class IsoscelesTriangle extends Shape {
    private double base;

    public IsoscelesTriangle(double base) {
        this.base = base;
    }

    @Override
    public double getWidth() { return base; }

    @Override
    public double getHeight() { return base * Math.sqrt(3) / 2; }

    @Override
    public double getArea() { return base * base * Math.sqrt(3) / 4; }

    @Override
    public Shape clone() { return new IsoscelesTriangle(base); }
}