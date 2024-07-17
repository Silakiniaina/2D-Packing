package packing2Dshapes;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class HeuristicPacker {
    private double containerWidth;
    private double containerHeight;

    public HeuristicPacker(double containerWidth, double containerHeight) {
        this.containerWidth = containerWidth;
        this.containerHeight = containerHeight;
    }

    public List<Shape> pack(List<Shape> shapes) {
        List<Shape> packedShapes = new ArrayList<>();
        shapes.sort(Comparator.comparing(Shape::getArea).reversed());

        double currentX = 0;
        double currentY = 0;
        double rowHeight = 0;

        for (Shape shape : shapes) {
            boolean placed = false;
            for (double rotation : new double[]{0, Math.PI/2, Math.PI}) {
                shape.rotate(rotation);
                if (currentX + shape.getWidth() <= containerWidth &&
                    currentY + shape.getHeight() <= containerHeight) {
                    shape.setPosition(currentX, currentY);
                    packedShapes.add(shape.clone());
                    currentX += shape.getWidth();
                    rowHeight = Math.max(rowHeight, shape.getHeight());
                    placed = true;
                    break;
                }
            }

            if (!placed) {
                currentX = 0;
                currentY += rowHeight;
                rowHeight = 0;
                if (currentY + shape.getHeight() <= containerHeight) {
                    shape.setPosition(currentX, currentY);
                    packedShapes.add(shape.clone());
                    currentX += shape.getWidth();
                    rowHeight = shape.getHeight();
                } else {
                    break; // Container is full
                }
            }
        }

        return packedShapes;
    }
}