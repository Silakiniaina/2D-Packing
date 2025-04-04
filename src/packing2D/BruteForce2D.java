package packing2D;

import java.util.ArrayList;
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

    public List<Rectangle> pack(List<Rectangle> rectangles) {
        List<Rectangle> currentSolution = new ArrayList<>();
        packRecursive(rectangles, 0, currentSolution);
        return bestSolution;
    }

    private void packRecursive(List<Rectangle> rectangles, int index, List<Rectangle> currentSolution) {
        if (index == rectangles.size()) {
            double utilization = calculateUtilization(currentSolution);
            if (utilization > bestUtilization) {
                bestUtilization = utilization;
                bestSolution = new ArrayList<>(currentSolution);
            }
            return;
        }

        Rectangle currentRect = rectangles.get(index);
        List<Position> possiblePositions = findPossiblePositions(currentSolution, currentRect);

        for (Position pos : possiblePositions) {
            currentRect.setPosition(pos.x, pos.y);
            currentSolution.add(currentRect);
            packRecursive(rectangles, index + 1, currentSolution);
            currentSolution.remove(currentSolution.size() - 1);
        }
    }

    private List<Position> findPossiblePositions(List<Rectangle> placedRectangles, Rectangle newRect) {
        List<Position> positions = new ArrayList<>();
        positions.add(new Position(0, 0)); // Always try top-left corner

        for (Rectangle placed : placedRectangles) {
            positions.add(new Position(placed.getX() + placed.getWidth(), placed.getY()));
            positions.add(new Position(placed.getX(), placed.getY() + placed.getHeight()));
        }

        positions.removeIf(pos -> !isValidPosition(placedRectangles, newRect, pos));
        return positions;
    }

    private boolean isValidPosition(List<Rectangle> placedRectangles, Rectangle newRect, Position pos) {
        if (pos.x + newRect.getWidth() > containerWidth || pos.y + newRect.getHeight() > containerHeight) {
            return false;
        }

        for (Rectangle placed : placedRectangles) {
            if (pos.x < placed.getX() + placed.getWidth() &&
                pos.x + newRect.getWidth() > placed.getX() &&
                pos.y < placed.getY() + placed.getHeight() &&
                pos.y + newRect.getHeight() > placed.getY()) {
                return false;
            }
        }

        return true;
    }

    private double calculateUtilization(List<Rectangle> solution) {
        double totalArea = 0;
        for (Rectangle rect : solution) {
            totalArea += rect.getArea();
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