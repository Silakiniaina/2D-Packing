// File: src/packing2D/NFDH.java
package packing2D;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class NFDH {
    private double containerWidth;
    private double containerHeight;

    public NFDH(double containerWidth, double containerHeight) {
        this.containerWidth = containerWidth;
        this.containerHeight = containerHeight;
    }

    public List<Rectangle> pack(List<Rectangle> rectangles) {
        List<Rectangle> packedRectangles = new ArrayList<>();
        
        // Sort rectangles by height in descending order
        rectangles.sort(Comparator.comparing(Rectangle::getHeight).reversed());
        
        double currentX = 0;
        double currentY = 0;
        double levelHeight = 0;

        for (Rectangle rect : rectangles) {
            if (currentX + rect.getWidth() > containerWidth) {
                currentX = 0;
                currentY += levelHeight;
                levelHeight = 0;
            }

            if (currentY + rect.getHeight() > containerHeight) {
                break;  // Container is full
            }

            rect.setPosition(currentX, currentY);
            packedRectangles.add(rect);

            currentX += rect.getWidth();
            levelHeight = Math.max(levelHeight, rect.getHeight());
        }

        return packedRectangles;
    }
}