package packing1D;

import java.util.ArrayList;
import java.util.List;

public class FirstFit {

    /* --------- Function to pack a list of item using the firstFit algo -------- */
    public static List<Bin> pack(List<Item> items, double binCapacity) {
        List<Bin> bins = new ArrayList<>();

        for (Item item : items) {
            boolean packed = false;
            for (Bin bin : bins) {
                if (bin.addItem(item)) {
                    packed = true;
                    break;
                }
            }
            if (!packed) {
                Bin newBin = new Bin(binCapacity);
                newBin.addItem(item);
                bins.add(newBin);
            }
        }

        return bins;
    }
}