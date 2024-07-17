package packing1D;

import java.util.ArrayList;
import java.util.List;

public class BruteForce1D{
    private static List<Bin> bestSolution;
    private static int minBins;

    /* ---------------------------- Function to pack ---------------------------- */
    public static List<Bin> pack(List<Item> items, double binCapacity) {
        bestSolution = null;
        minBins = Integer.MAX_VALUE;
        
        List<Bin> currentSolution = new ArrayList<>();
        packRecursive(items, 0, currentSolution, binCapacity);
        
        return bestSolution;
    }

    /* ----------------- Function to pack recurcively the items ----------------- */
    private static void packRecursive(List<Item> items, int index, List<Bin> currentSolution, double binCapacity) {
        if (index == items.size()) {
            if (currentSolution.size() < minBins) {
                minBins = currentSolution.size();
                bestSolution = new ArrayList<>(currentSolution);
            }
            return;
        }

        Item currentItem = items.get(index);

        for (int i = 0; i < currentSolution.size(); i++) {
            Bin bin = currentSolution.get(i);
            if (bin.getRemainingSpace() >= currentItem.getSize()) {
                bin.addItem(currentItem);
                packRecursive(items, index + 1, currentSolution, binCapacity);
                bin.getItems().remove(currentItem);
            }
        }

        Bin newBin = new Bin(binCapacity);
        newBin.addItem(currentItem);
        currentSolution.add(newBin);
        packRecursive(items, index + 1, currentSolution, binCapacity);
        currentSolution.remove(currentSolution.size() - 1);
    }
}