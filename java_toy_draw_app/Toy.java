public class Toy {

    private int id;
    private String name;
    private int quantity;
    private int dropRate;
    
    public Toy(int id, String name, int quantity, int dropRate) {
        
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.dropRate = dropRate;
        
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getDropRate() {
        return dropRate;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public void setDropRate(int dropRate) {
        this.dropRate = dropRate;
    }

    @Override
    public String toString(){
        return id + "," + name + "," + quantity + "," + dropRate + "%";
    }

}
