import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private static RecipeBook book;
    private static ObjectMapper mapper = new ObjectMapper();
    private static int choice = 0;
    private static JsonReader reader = new JsonReader(mapper);
    private static String recipesFilePath = "recipeBook.json";

    public static void main(String[] args) throws IOException, ParseException {
        System.out.println("Welcome to EJJ's Digital Recipe Book!");
        System.out.println("To choose an action, type in the number corresponding to that action. Happy cooking!");
        book = new RecipeBook(reader.getRecipesFromJsonFile(recipesFilePath));
        boolean program = true;

        while (program) {
            Scanner function = new Scanner(System.in);
            System.out.println("\nWhich Function Do You Want? (1) Create recipe (2) Search recipe (3) Browse all recipes (4) Quit");
            System.out.print("Enter your input: ");
            try {   //input validation
                choice = function.nextInt();
            }

            catch (InputMismatchException e){
                System.out.println("Enter a valid input");
            }

            switch (choice) {

                /**
                 * More functions could be added if you want.
                 * */
                case 1:
                    reader.createRecipe(book);
                    break;

                case 2:
                    // to be modified by Joanne
                    Scanner read = new Scanner(System.in);
                    System.out.println("\nEnter the name of Recipe You want to search: (ex) Cake");
                    System.out.print("Enter your input: ");
                    String recipeName = read.next().toLowerCase();
                    Recipe found = reader.searchRecipeBook(book, recipeName); // all further search process should be done at "search" function at JsonReader.
                    if (found != null){
                        reader.exploreRecipe(found);
                    }

                    break;

                case 3:
                    reader.browseRecipes(book);
                    break;

                case 4:
                    System.out.println("End the program.");
                    program = false;
                    break;

                default:
                    System.out.println("Enter Correct Option: ");
                    break;
            }
        }
    }
}
