import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class JsonReader {

    public JsonReader(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    static Recipe recipe;
    static RecipeBook book = new RecipeBook();
    static ObjectMapper mapper = new ObjectMapper();
    static String choice = null;
    static JsonReader reader = new JsonReader(mapper);
    static String cont1 = null;
    static String cont2 = null;
    static ArrayList<String> ingredients = new ArrayList<String>();
    static ArrayList<String> instructions = new ArrayList<String>();
    static String recipesFilePath = "recipeBook.json";

    public static void createRecipe() {
        ArrayList<String> ingredients = new ArrayList<String>();
        ArrayList<String> instructions = new ArrayList<String>();

        Scanner input = new Scanner(System.in);
        System.out.println("What is the name of this recipe?: ");
        String name = input.next();

        Scanner input1 = new Scanner(System.in);
        System.out.println("Please enter a short description?: ");
        String description = input1.nextLine();

        while (true) {
            Scanner input2 = new Scanner(System.in);
            System.out.println("Add an ingredient: ");

            String ingredient = input2.nextLine();
            ingredients.add(ingredient);

            System.out.println("Are you going to add another? Yes or No: ");
            cont1 = input1.next();

            if (cont1.toLowerCase().equals("no")) {
                break;
            } else if (cont1.toLowerCase().equals("yes")) {
                ingredients.add(input2.next());

                System.out.println("Are you going to add another? Yes or No: ");
                cont1 = input1.next();

                if (cont1.toLowerCase().equals("no")) {
                    break;
                } else if (cont1.toLowerCase().equals("yes")) {
                    continue;
                } else {
                    System.out.println("Type only Yes or No.");
                }
            }
        }

        while (true) {
            Scanner input3 = new Scanner(System.in);
            System.out.println("Please add new step to the instructions: ");
            String instruction = input3.nextLine();
            instructions.add(instruction);

            System.out.println("Are you going to add another? Yes or No: ");
            cont2 = input3.next();

            if (cont2.toLowerCase().equals("no")) {
                break;
            } else if (cont2.toLowerCase().equals("yes")) {
                instructions.add(input3.nextLine());

                System.out.println("Are you going to add another? Yes or No: ");
                cont2 = input3.next();

                if (cont2.toLowerCase().equals("no")) {
                    break;
                } else if (cont2.toLowerCase().equals("yes")) {
                    continue;
                } else {
                    System.out.println("Type only Yes or No.");
                }
            }
        }

        Recipe newRecipe = new Recipe(name, description, ingredients, instructions);
        book.recipeBook.add(newRecipe);
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

    public static void searchJsonFile(String name) {

        if (mapper == null) {
            System.out.println("No such info");
        } else {
            try {
                System.out.println(mapper.writeValueAsString(book.getRecipe(name)));
            } catch (IndexOutOfBoundsException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
