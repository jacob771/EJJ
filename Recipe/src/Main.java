import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main (String[] args) {

        Recipe storage = new Recipe();

        boolean recipe_search = false;
        ArrayList<String> storage_returned = storage.getStorage();

        while (true) {
            Scanner input = new Scanner(System.in);
            System.out.println("Enter the name of Recipe: ");
            String recipe = input.next();

            String findReceipt = storage.pull_receipt(recipe);

            if (findReceipt != null) {
                System.out.println("The Receipt you try to access does exist.");
                System.out.println("Its ingredients include:");

                int index = storage_returned.indexOf(findReceipt);
                String [] ingredients = storage_returned.get(index);

                for (int i = 0; i < ) {

                }
            }

            input.close();
        }
    }
}
