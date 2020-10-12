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

    /**
     * Main function handling the program logic
     * @param args
     * @throws IOException
     * @throws ParseException
     */
    public static void main (String[] args) throws IOException, ParseException {
        String recipesFilePath = "recipeBook.json";
        RecipeBook recipeBook = new RecipeBook(getRecipesFromJsonFile(recipesFilePath));
        Scanner input = new Scanner(System.in);
        String answer = null;
        Boolean askForNew = null;
        do {
            System.out.println("Would you like to add a new recipe? (yes/no)");
            answer = input.nextLine();
            if(answer.toLowerCase().equals("no")) { askForNew = false; }
            else if (answer.toLowerCase().equals("yes")) {
                askForNew = true;
                Recipe newRecipe = createRecipe(input);
                recipeBook.getRecipeBook().add(newRecipe);
            }
            else { askForNew = true; }
        } while(answer.toLowerCase().equals("yes"));

        addToJsonFile(recipeBook.getRecipeBook(), recipesFilePath);
    }
    //TODO add UI to the main function

    /**
     * Creates a recipe object using user input
     * @param input
     * @return
     */
    public static Recipe createRecipe(Scanner input)
    {
        ArrayList<String> ingredients = new ArrayList<String>();
        ArrayList<String> instructions = new ArrayList<String>();

        System.out.println("What is the name of this recipe?: ");
        String name = input.nextLine();
        System.out.println("Please enter a short description?: ");
        String description = input.nextLine();
        Boolean addToList = true;
        while (addToList) {
            addToList = false;
            System.out.println("Add an ingredient, if none type 'none': ");
            String ingredient = input.nextLine();
            if(!(ingredient.toLowerCase().equals("none"))) {
                ingredients.add(ingredient);
                addToList = true;
            }
        }

        addToList = true;
        int num = 1;
        while (addToList) {
            addToList = false;
            System.out.println("Please add a step to the instructions, if none type 'none': ");
            String instruction = input.nextLine();
            if(!(instruction.toLowerCase().equals("none"))) {
                String formatted = String.format("%d.) %s", num,instruction);
                instructions.add(formatted);
                num++;
                addToList = true;
            }
        }

        Recipe newRecipe = new Recipe(name, description, ingredients, instructions);
        return newRecipe;
    }

    /**
     * Reads the different recipes from the Json File and returns them as an ArrayList of Recipes
     * @param filePath
     * @return the Recipes as an arrayList
     */
    public static ArrayList<Recipe> getRecipesFromJsonFile(String filePath)  {
        ArrayList<Recipe> recipes = new ArrayList<>();
        File recipeFile = new File(filePath);
        if(recipeFile.length() == 0) {
            return recipes;
        }

        try{
            JSONParser parser = new JSONParser();
            JSONArray a = (JSONArray) parser.parse(new FileReader(filePath));
            for(Object o : a) {
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
     * @param recipes
     * @param filePath
     */
    public static void addToJsonFile(ArrayList<Recipe> recipes, String filePath) {
        ObjectMapper mapper = new ObjectMapper();
        try{
            System.out.println(mapper.writeValueAsString(recipes));
            mapper.writeValue(new File(filePath), recipes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
