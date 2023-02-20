import java.io.File;
import java.io.FileNotFoundException;
import javax.swing.JFileChooser;

public class Main {
    public static void main(String[] args){

        System.out.println("Let the game begin!");
        
        ToyStore toyStore = new ToyStore();
        Toy toy1 = new Toy(1, "Teddy Bear", 10, 10);
        Toy toy2 = new Toy(2, "Doll", 5, 15);
        toyStore.addToy(toy1);
        toyStore.addToy(toy2);

//        toyStore.setDropRate(1,75);


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

        Toy prizeToy = toyStore.startDraw();
        if (prizeToy != null) {
            System.out.println("Congratulations, you've won a " + prizeToy.getName() + " at " + prizeToy.getDropRate() + "% drop rate" + "!");
            System.out.println(prizeToy);
            for (Toy toy : toyStore.getToys()) {
                System.out.println(toy);
            }
        } else {
            System.out.println("Sorry, no toys available to draw.");
        }

    }
}
