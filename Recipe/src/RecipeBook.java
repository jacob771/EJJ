//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
//import org.json.simple.parser.ParseException;

import java.util.*;
import java.io.*;

public class RecipeBook {
    ArrayList<Recipe> recipeBook = new ArrayList<Recipe>();

    public RecipeBook (ArrayList<Recipe> recipeBook) {
    }

    public RecipeBook() {}

    /**
     * Getter
     * */
    public ArrayList<Recipe> getRecipeBook() {
        return this.recipeBook;
    }

    /**
     * Add new Recipe
     */
    private void addRecipe (Recipe recipe) {
        recipeBook.add(recipe);
    }

    /**
     * Delete Recipe
     * */
    private void deleteRecipe (Recipe recipe) {
        int index = recipeBook.indexOf(recipe);
        recipeBook.remove(index);
    }

    Recipe getRecipe(String name) {
        int index = recipeBook.indexOf(name);
        Recipe recipe = recipeBook.get(index);
        return recipe;
    }

    private void modifyRecipe (String name, String description, ArrayList<String> ingredients, ArrayList<String> instructions) {
        Recipe recipe = getRecipe(name);
        recipeBook.remove(recipe);
        recipe.setRecipe(name, description, ingredients, instructions);
        recipeBook.add(recipe);
    }

    private void createRecipe (String name, String description, ArrayList<String> ingredients, ArrayList<String> instructions) {
        Recipe newRecipe = new Recipe(name, description, ingredients, instructions);
        recipeBook.add(newRecipe);
    }
}






