package packing2D;

import java.util.List;

public class BruteForce2D {
    private double containerWidth;
    private double containerHeight;
    private List<Rectangle> bestSolution;
    private double bestUtilization;

    public BruteForce2D(double containerWidth, double containerHeight) {
        this.containerWidth = containerWidth;
        this.containerHeight = containerHeight;
        this.bestSolution = null;
        this.bestUtilization = 0;
    }


}