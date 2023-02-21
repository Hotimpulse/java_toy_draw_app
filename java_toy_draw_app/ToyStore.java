import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ToyStore {

    private List<Toy> toys;

    public ToyStore() {
        toys = new ArrayList<>();
    }

    public void addToy(Toy toy) {
        toys.add(toy);
    }

//    public void setDropRate(int id, int dropRate) {
//        for (Toy toy: toys) {
//            if (toy.getId() == id) {
//                toy.setDropRate(dropRate);
//                break;
//            }
//        }
//    }

    public void addFromFile(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] fields = line.split(",(?![^()]*\\))");
            int id = Integer.parseInt(fields[0].trim());
            String name = fields[1].trim();
            int quantity = Integer.parseInt(fields[2].trim());
            int dropRate = Integer.parseInt(fields[3].replaceAll("%", "").trim());;
            Toy toy = new Toy(id, name, quantity, dropRate);
            addToy(toy);
        }
        scanner.close();
    }


    public Toy startDraw() {
    
        List<Toy> prizeList = new ArrayList<>();
        for (Toy toy : toys) {
            int numPrizes = (int) Math.round((double) toy.getQuantity() * toy.getDropRate() / 100);
            for (int i = 0; i < numPrizes; i++) {
                prizeList.add(toy);
            }
        }
        if (prizeList.isEmpty()) {
            return null;
        } else {
            Toy selectedToy = prizeList.get((int) Math.floor(Math.random() * prizeList.size()));
            selectedToy.setQuantity(selectedToy.getQuantity() - 1);
            return selectedToy;
        }
    }

    public List<Toy> getToys() {
        return toys;
    }
}