package packing2D;

import java.util.List;
import java.util.ArrayList;

public class BestFit2D {
    
    /* ------------------ packing using the bestFit of 2d algo ------------------ */
    public static List<Rectangle> pack(List<Rectangle> rectangles, double containerWidth, double containerHeight) {
        List<Rectangle> packedRectangles = new ArrayList<>();
        List<Rectangle> gaps = new ArrayList<>();
        gaps.add(new Rectangle(containerWidth, containerHeight));

        for (Rectangle rect : rectangles) {
            Rectangle bestGap = null;
            int bestGapIndex = -1;
            double minWastedSpace = Double.MAX_VALUE;

            for (int i = 0; i < gaps.size(); i++) {
                Rectangle gap = gaps.get(i);
                if (rect.getWidth() <= gap.getWidth() && rect.getHeight() <= gap.getHeight()) {
                    double wastedSpace = (gap.getWidth() * gap.getHeight()) - rect.getArea();
                    if (wastedSpace < minWastedSpace) {
                        bestGap = gap;
                        bestGapIndex = i;
                        minWastedSpace = wastedSpace;
                    }
                }
            }

            if (bestGap != null) {
                rect.setPosition(bestGap.getX(), bestGap.getY());
                packedRectangles.add(rect);
                gaps.remove(bestGapIndex);

                // Create new gaps
                if (bestGap.getWidth() > rect.getWidth()) {
                    gaps.add(new Rectangle(bestGap.getWidth() - rect.getWidth(), rect.getHeight()));
                }
                if (bestGap.getHeight() > rect.getHeight()) {
                    gaps.add(new Rectangle(bestGap.getWidth(), bestGap.getHeight() - rect.getHeight()));
                }
            }
        }

        return packedRectangles;
    }
}