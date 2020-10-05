import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main (String[] args) {
        Storage storage = new Storage();

        while (true) {
            Scanner input = new Scanner(System.in);

            System.out.println("If you want to search recipe in the database, please enter the name of recipe or part of ingredients: ");
            String recipe = input.next();

            if (storage.storage.contains(recipe)) {
                int pick = storage.storage.indexOf(recipe);
                Object choice = storage.storage.get(pick);
            }

            input.close();

            System.out.println("Wanna Type Another Input?: Yes or No (lowercase or uppercase does not matter)");
            String cont = input.next();
            cont.toLowerCase();

            if (cont == "yes") {
                continue;
            }

            else if (cont == "no") {
                break;
            }

            else {
                System.out.println("Wrong Command! Options are only yes/no.");
            }
        }
    }
}
