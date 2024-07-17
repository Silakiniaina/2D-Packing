package packing1D;

import java.util.ArrayList;
import java.util.List;

public class BestFit {

    /* --------- Function to pack a list of items using the bestFit algo -------- */
    public static List<Bin> pack(List<Item> items, double binCapacity) {
        List<Bin> bins = new ArrayList<>();

        for (Item item : items) {
            Bin bestBin = null;
            double minRemainingSpace = Double.MAX_VALUE;

            for (Bin bin : bins) {
                if (bin.getRemainingSpace() >= item.getSize() && bin.getRemainingSpace() - item.getSize() < minRemainingSpace) {
                    bestBin = bin;
                    minRemainingSpace = bin.getRemainingSpace() - item.getSize();
                }
            }

            if (bestBin == null) {
                bestBin = new Bin(binCapacity);
                bins.add(bestBin);
            }

            bestBin.addItem(item);
        }

        return bins;
    }
}