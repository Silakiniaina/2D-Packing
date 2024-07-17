package packing2D;

import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;

public class NFDH {

    /* ------------------------- Algo to pack using NFDH ------------------------ */
    public static List<Rectangle> pack(List<Rectangle> rectangles, double containerWidth, double containerHeight) {
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