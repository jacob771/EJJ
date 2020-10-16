import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class JsonReader {
    private static Recipe recipe;
    private static RecipeBook book = new RecipeBook();
    private static ObjectMapper mapper = new ObjectMapper();
    private static boolean cont = true;
    private static ArrayList<String> ingredients = new ArrayList<String>();
    private static ArrayList<String> instructions = new ArrayList<String>();
    private static String recipesFilePath = "recipeBook.json";

    public JsonReader(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    public static void createRecipe() {
        ArrayList<String> ingredients = new ArrayList<String>();
        ArrayList<String> instructions = new ArrayList<String>();

        Scanner input = new Scanner(System.in);
        System.out.println("What is the name of this recipe?: ");
        String name = input.next();

        Scanner descriptionInput = new Scanner(System.in);
        System.out.println("Please enter a short description?: ");
        String description = descriptionInput.nextLine();
        cont = true;

        while (cont) {
            Scanner ingredientInput = new Scanner(System.in);
            System.out.println("Add an ingredient: ");
            String ingredient = ingredientInput.nextLine();
            ingredients.add(ingredient);

            while (true) {
                Scanner inputCont = new Scanner(System.in);
                System.out.println("Are you going to add another? Yes or No: ");
                String inputContinue1 = inputCont.nextLine();

                if (inputContinue1.toLowerCase().equals("no") || inputContinue1.toLowerCase().equals("yes")) {
                    cont = (inputContinue1.toLowerCase().equals("no")) ? false : true;
                    break;
                } else {
                    System.out.println("Type only Yes or No.");
                    continue;
                }
            }

        }

        cont = true;
        while (cont) {
            Scanner input3 = new Scanner(System.in);
            System.out.println("Please add new step to the instructions: ");
            instructions.add(input3.next());

            while (true) {
                Scanner inputCont2 = new Scanner(System.in);
                System.out.println("Are you going to update another step? Yes or No: ");
                String inputContinue2 = inputCont2.next();

                if (inputContinue2.toLowerCase().equals("no") || inputContinue2.toLowerCase().equals("yes")) {
                    cont = (inputContinue2.toLowerCase().equals("no")) ? false : true;
                    break;
                } else {
                    System.out.println("Type only Yes or No.");
                    continue;
                }
            }
        }

        try {
            Recipe newRecipe = new Recipe(name, description, ingredients, instructions);
            book.recipeBook.add(newRecipe);
            mapper.writeValueAsString(newRecipe);
        }

        catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads the different recipes from the Json File and returns them as an ArrayList of Recipes
     *
     * @param filePath
     * @return the Recipes as an arrayList
     */
    public static ArrayList<Recipe> getRecipesFromJsonFile(String filePath) {
        ArrayList<Recipe> recipes = new ArrayList<>();
        File recipeFile = new File(filePath);
        if (recipeFile.length() == 0) {
            return recipes;
        }

        try {
            JSONParser parser = new JSONParser();
            JSONArray a = (JSONArray) parser.parse(new FileReader(filePath));
            for (Object o : a) {
                JSONObject recipe = (JSONObject) o;
                String name = (String) recipe.get("name");
                String description = (String) recipe.get("description");
                ArrayList<String> ingredients = (ArrayList<String>) recipe.get("ingredients");
                ArrayList<String> instructions = (ArrayList<String>) recipe.get("instructions");
                recipes.add(new Recipe(name, description, ingredients, instructions));
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return recipes;
    }

    /**
     * Formats and adds the Recipe arrayList to the Json File
     *
     * @param recipes
     * @param filePath
     */
    public static void addToJsonFile(ArrayList<Recipe> recipes, String filePath) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            System.out.println(mapper.writeValueAsString(recipes));
            mapper.writeValue(new File(filePath), recipes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void searchJsonFile(String filePath) throws IOException {
        if (mapper == null) {
            System.out.println("No such info");
        } else {
            try {
                System.out.println(mapper.writeValueAsString(book.getRecipe(filePath)));
            } catch (IndexOutOfBoundsException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
