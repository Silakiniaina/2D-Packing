// File: src/packing2D/FFDH.java
package packing2D;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class FFDH {
    private double containerWidth;
    private double containerHeight;

    public FFDH(double containerWidth, double containerHeight) {
        this.containerWidth = containerWidth;
        this.containerHeight = containerHeight;
    }

    public List<Rectangle> pack(List<Rectangle> rectangles) {
        List<Rectangle> packedRectangles = new ArrayList<>();
        
        // Sort rectangles by height in descending order
        rectangles.sort(Comparator.comparing(Rectangle::getHeight).reversed());
        
        List<Level> levels = new ArrayList<>();

        for (Rectangle rect : rectangles) {
            boolean placed = false;
            for (Level level : levels) {
                if (level.canFit(rect)) {
                    level.addRectangle(rect);
                    packedRectangles.add(rect);
                    placed = true;
                    break;
                }
            }

            if (!placed) {
                if (levels.isEmpty() || levels.get(levels.size() - 1).getY() + rect.getHeight() <= containerHeight) {
                    Level newLevel = new Level(containerWidth, levels.isEmpty() ? 0 : levels.get(levels.size() - 1).getY() + levels.get(levels.size() - 1).getHeight());
                    newLevel.addRectangle(rect);
                    levels.add(newLevel);
                    packedRectangles.add(rect);
                } else {
                    break;  // Container is full
                }
            }
        }

        return packedRectangles;
    }

    private class Level {
        private double width;
        private double y;
        private double height;
        private double usedWidth;

        public Level(double width, double y) {
            this.width = width;
            this.y = y;
            this.height = 0;
            this.usedWidth = 0;
        }

        public boolean canFit(Rectangle rect) {
            return usedWidth + rect.getWidth() <= width;
        }

        public void addRectangle(Rectangle rect) {
            rect.setPosition(usedWidth, y);
            usedWidth += rect.getWidth();
            height = Math.max(height, rect.getHeight());
        }

        public double getY() {
            return y;
        }

        public double getHeight() {
            return height;
        }
    }
}