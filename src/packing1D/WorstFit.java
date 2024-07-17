package packing1D;

import java.util.ArrayList;
import java.util.List;

public class WorstFit {

    /* --------- Function to pack a list of item using the worstFit algo -------- */
    public static List<Bin> pack(List<Item> items, double binCapacity) {
        List<Bin> bins = new ArrayList<>();

        for (Item item : items) {
            Bin worstBin = null;
            double maxRemainingSpace = -1;

            for (Bin bin : bins) {
                if (bin.getRemainingSpace() >= item.getSize() && bin.getRemainingSpace() > maxRemainingSpace) {
                    worstBin = bin;
                    maxRemainingSpace = bin.getRemainingSpace();
                }
            }

            if (worstBin == null) {
                worstBin = new Bin(binCapacity);
                bins.add(worstBin);
            }

            worstBin.addItem(item);
        }

        return bins;
    }
}