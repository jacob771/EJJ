import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main (String[] args) {

        Storage storage = new Storage();

        while (true) {
            Scanner input = new Scanner(System.in);

            System.out.println("If you want to search recipe in the database, please enter the name of recipe or part of ingredients: ");
            String recipe = input.next();

            if (storage.storage) {

            }

            if (findReceipt != null) {
                System.out.println("The Receipt you try to access does exist.");
                System.out.println("Its ingredients include:");

                int index = storage_returned.indexOf(findReceipt);
                String [] ingredients = storage_returned.get(index);

                for (int i = 0; i < ) {

                }
            }

            input.close();

            System.out.println("Wanna Type Another Input?: Yes or No");
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
