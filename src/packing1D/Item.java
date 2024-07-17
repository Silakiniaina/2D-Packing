package packing1D;

public class Item {
    private double size;

    /* ------------------------------- Constructor ------------------------------ */
    public Item(double size) {
        this.setSize(size);
    }

    /* --------------------------------- Getters -------------------------------- */
    public double getSize() {
        return size;
    }

    /* --------------------------------- Setters -------------------------------- */
    public void setSize(double size){
        this.size = size;
    }
}