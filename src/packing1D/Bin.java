package packing1D;

import java.util.ArrayList;
import java.util.List;

public class Bin {
    private double capacity;
    private double remainingSpace;
    private List<Item> items;

    /* ------------------------------- Constructor ------------------------------ */
    public Bin(double capacity) {
        this.setCapacity(capacity);
        this.setRemainingSpace(capacity);
        this.setItems(new ArrayList<>());
    }

    /* ------------------ Function to add an item into the bin ------------------ */
    public boolean addItem(Item item) {
        if (item.getSize() <= remainingSpace) {
            items.add(item);
            remainingSpace -= item.getSize();
            return true;
        }
        return false;
    }

    /* --------------------------------- Getters -------------------------------- */
    public double getCapacity() {
        return capacity;
    }
    public double getRemainingSpace() {
        return remainingSpace;
    }
    public List<Item> getItems() {
        return items;
    }
    
    /* --------------------------------- Setters -------------------------------- */
    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    public void setRemainingSpace(double remainingSpace) {
        this.remainingSpace = remainingSpace;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
    
    
}