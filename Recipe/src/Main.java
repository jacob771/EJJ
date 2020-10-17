import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static Recipe recipe = new Recipe();
    private static RecipeBook book = new RecipeBook();
    private static ObjectMapper mapper = new ObjectMapper();
    private static String choice = null;
    private static JsonReader reader = new JsonReader(mapper);
    private static ArrayList<String> ingredients = new ArrayList<String>();
    private static ArrayList<String> instructions = new ArrayList<String>();
    private static String recipesFilePath = "recipeBook.json";

    public static void main(String[] args) throws IOException, ParseException {

        boolean program = true;

        while (program) {
            Scanner function = new Scanner(System.in);
            System.out.println("Which Function Do You Want? (1) Delete (2) Create (3) Search (4) Quit");
            choice = function.next().toLowerCase();
            switch (choice) {

                /**
                 * More functions could be added if you want.
                 * */

                case "delete":
                    reader.deleteJsonFile(recipesFilePath);
                    break;

                case "create":
                    reader.createRecipe();
                    break;

                case "search":
                    // to be modified by Joanne
                    Scanner read = new Scanner(System.in);
                    System.out.println("Enter the name of Recipe You want to search: (ex) Cake");
                    String recipeName = read.next().toLowerCase();
                    String thisFilePath = "filePath"; // need to get filePath from recipeName.
                    reader.searchJsonFile(thisFilePath); // all further search process should be done at "search" function at JsonReader.
                    break;

                case "quit":
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