import java.util.*;
import java.io.*;

public class RecipeBook {
    ArrayList<Recipe> recipeBook = new ArrayList<Recipe>();

    public RecipeBook(ArrayList<Recipe> recipeBook) {
        this.recipeBook = recipeBook;
    }

    /**
     * Put Certain Input, then it searches the Recipe.
     */
    private Recipe searchRecipeBook(String name) {
        if (recipeBook.contains(name)) {
            int index = recipeBook.indexOf(name);
            return recipeBook.get(index);
        }

        else {
            return null;
        }
    }

    private Recipe searchRecipeBook(String [] ingredients) {

    }

    /**
     * Add new Recipe
     */
    private void addRecipe(Recipe recipe) {
        recipeBook.add(recipe);
    }
}