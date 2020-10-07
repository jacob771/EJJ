import java.util.Scanner;

public class Main {
    public Main() {
    }

    public static void main (String[] args) {
        RecipeBook recipeBook = new RecipeBook();

        while (true) {

            /**
             * I/O Function
             * */

            Scanner input = new Scanner(System.in);

            System.out.println("If you want to search recipe in the database, please enter the name of recipe or part of ingredients: ");
            String recipe = input.next();

            /**
             * Create New Recipe and Store in RecipeBook
             * */

            if (recipeBook.storage.contains(recipe)) {
                int pick = recipeBook.storage.indexOf(recipe);
                Object choice = recipeBook.storage.get(pick);
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
