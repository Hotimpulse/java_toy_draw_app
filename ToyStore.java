package java_toy_draw_app;

public class ToyStore {

    public static void main(String[] args){

        Toy[] toys = new Toy[10];
        toys[0] = new Toy(1, "lego", 2, 50);
        for (Toy toy: toys) {
            System.out.println(toy);
        }
    }


}