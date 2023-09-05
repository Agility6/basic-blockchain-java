import java.util.Scanner;

public class Main {

    public static final Scanner keyboard = new Scanner(System.in);

    public static void main(String[] args) {

        // Create My Chain
        Chain ag_chain = new Chain(keyboard,5);
        ag_chain.Start();

        keyboard.close();
    }
}