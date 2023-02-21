import java.io.*;
import java.util.List;
import javax.swing.JFileChooser;

public class Main {
    public static void main(String[] args) {

        System.out.println("Locate the file toys.txt to begin the game!");
        try {
            ToyStore toyStore = new ToyStore();
            List<Toy> toyList = toyStore.getToys();
            pickFile(toyStore);
            System.out.println("Here is a list of all toys before the prize drop: ");
            for (Toy toy : toyStore.getToys()) {
                System.out.println(toy);
            }
            System.out.println();
            System.out.println("Initiating draw...");
            System.out.println();
            drawPrize(toyStore, toyList);
        } catch (Exception e) {
            System.out.println("There is an error:" + e.getMessage());
        }
    }


    public static void pickFile(ToyStore toyStore) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    try {
                        toyStore.addFromFile(file);
                    } catch (FileNotFoundException e) {
                        System.out.println("File not found: " + e.getMessage());
                    }
                }
        }

        public static void drawPrize(ToyStore toyStore, List<Toy> toyList) {
            Toy prizeToy = toyStore.startDraw();
            if (prizeToy != null) {
                System.out.println("Congratulations, you've won a " + prizeToy.getName() + " at " + prizeToy.getDropRate() + "% drop rate" + "!");
                System.out.println();


                try {
                    updateToyData(prizeToy, toyList); // update the toy data
                    writePrize(prizeToy); // write the prize to the prize file
                    System.out.println("Here is a list of all toys after the prize drop: ");
                    for (Toy toy : toyStore.getToys()) {
                        System.out.println(toy);
                    }
                } catch (IOException e) {
                    System.out.println("IOException: " + e.getMessage());
                }
            } else {
                System.out.println("Sorry, no toys available to draw.");
            }
        }

    public static void updateToyData(Toy prizeToy, List<Toy> toyList) throws IOException {

        // Calculate the total quantity of all toys
        int totalQuantity = 0;
        for (Toy toy : toyList) {
            totalQuantity += toy.getQuantity();
        }

        for (Toy toy : toyList) {
            int quantity = toy.getQuantity();
            int dropRate = (int) Math.round((1.0 * quantity / totalQuantity) * 100);
            toy.setDropRate(dropRate);

            // Set the drop rate to 0 if there are no more toys left
            if (quantity == 0) {
                toy.setDropRate(0);
            }
        }

        File outputFile = new File("./java_toy_draw_app/toys.txt"); //update original toys file
        FileWriter writer = new FileWriter(outputFile);
        for (Toy line : toyList) {
            writer.write(line + "\n");
        }
        writer.close();
    }
        public static void writePrize(Toy prizeToy) throws IOException {
            BufferedWriter writer = new BufferedWriter(new FileWriter("./java_toy_draw_app/prizes.txt", true));
            writer.write(prizeToy.getId() + "," + prizeToy.getName() + "," + prizeToy.getDropRate() + "\n");
            writer.close();
        }
}
