import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

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
            System.out.println("Which Function Do You Want? (1) Delete (3) Create (4) Search (5) Quit");
            choice = function.next().toLowerCase();
            switch (choice) {

                case "delete":

                case "create":
                    reader.createRecipe();
                    break;

                case "search":
                    Scanner read = new Scanner(System.in);
                    System.out.println("Do you want to print any kind of recipe? Then put the name of the recipe: ");
                    String recipeName = read.next();
                    reader.searchJsonFile(recipeName);
                    break;

                case "quit":
                    System.out.println("End the program.");
                    program = false;
                    break;

                default:
                    System.out.println("Enter Correct Option: ");
            }
        }
    }
}