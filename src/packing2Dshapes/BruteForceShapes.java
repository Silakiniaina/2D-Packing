package packing2Dshapes;

import java.util.ArrayList;
import java.util.List;

public class BruteForceShapes {
    private double containerWidth;
    private double containerHeight;
    private List<Shape> bestSolution;
    private double bestUtilization;

    public BruteForceShapes(double containerWidth, double containerHeight) {
        this.containerWidth = containerWidth;
        this.containerHeight = containerHeight;
        this.bestSolution = null;
        this.bestUtilization = 0;
    }

    public List<Shape> pack(List<Shape> shapes) {
        List<Shape> currentSolution = new ArrayList<>();
        packRecursive(shapes, 0, currentSolution);
        return bestSolution;
    }

    private void packRecursive(List<Shape> shapes, int index, List<Shape> currentSolution) {
        if (index == shapes.size()) {
            double utilization = calculateUtilization(currentSolution);
            if (utilization > bestUtilization) {
                bestUtilization = utilization;
                bestSolution = new ArrayList<>(currentSolution);
            }
            return;
        }

        Shape currentShape = shapes.get(index);
        List<Position> possiblePositions = findPossiblePositions(currentSolution, currentShape);

        for (Position pos : possiblePositions) {
            for (double rotation : new double[] { 0, Math.PI / 2, Math.PI }) {
                Shape clonedShape = currentShape.clone();
                clonedShape.rotate(rotation);
                clonedShape.setPosition(pos.x, pos.y);
                if (isValidPosition(currentSolution, clonedShape,pos)) {
                    currentSolution.add(clonedShape);
                    packRecursive(shapes, index + 1, currentSolution);
                    currentSolution.remove(currentSolution.size() - 1);
                }
            }
        }
    }

    private List<Position> findPossiblePositions(List<Shape> placedShapes, Shape newShape) {
        List<Position> positions = new ArrayList<>();
        positions.add(new Position(0, 0)); // Always try top-left corner

        for (Shape placed : placedShapes) {
            positions.add(new Position(placed.getX() + placed.getWidth(), placed.getY()));
            positions.add(new Position(placed.getX(), placed.getY() + placed.getHeight()));
        }

        positions.removeIf(pos -> !isValidPosition(placedShapes, newShape, pos));
        return positions;
    }

    private boolean isValidPosition(List<Shape> placedShapes, Shape newShape, Position pos) {
        if (pos.x + newShape.getWidth() > containerWidth || pos.y + newShape.getHeight() > containerHeight) {
            return false;
        }

        for (Shape placed : placedShapes) {
            if (shapesOverlap(placed, newShape, pos)) {
                return false;
            }
        }

        return true;
    }

    private boolean shapesOverlap(Shape shape1, Shape shape2, Position pos2) {
        // Pour simplifier, nous utilisons un rectangle englobant pour chaque forme
        double x1 = shape1.getX();
        double y1 = shape1.getY();
        double w1 = shape1.getWidth();
        double h1 = shape1.getHeight();

        double x2 = pos2.x;
        double y2 = pos2.y;
        double w2 = shape2.getWidth();
        double h2 = shape2.getHeight();

        return x1 < x2 + w2 && x1 + w1 > x2 && y1 < y2 + h2 && y1 + h1 > y2;
    }

    private double calculateUtilization(List<Shape> solution) {
        double totalArea = 0;
        for (Shape shape : solution) {
            totalArea += shape.getArea();
        }
        return totalArea / (containerWidth * containerHeight);
    }

    private static class Position {
        double x, y;

        Position(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }
}