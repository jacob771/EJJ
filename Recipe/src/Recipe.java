import java.util.*;

public class Recipe {
    private ArrayList<Object> Recipe = new ArrayList<>();
    private String name;
    private String description;
    private ArrayList<String> ingredients;
    private ArrayList<String> instructions;

    RecipeBook recipeBook = new RecipeBook();
    ArrayList <Recipe> database = recipeBook.storage;

    Recipe(String name, String description, ArrayList<String> ingredients, ArrayList<String> instructions) {
        this.name = name;
        this.description = description;
        this.ingredients = ingredients;
        this.instructions = instructions;
    }

    void setRecipe(String name, String description, ArrayList<String> ingredients, ArrayList<String> instructions) {
        Recipe recipe = new Recipe(name, description, ingredients, instructions);
    }

    void getRecipe(String name) {
    }

    void getRecipe(String description) {

    }

    void getRecipe(ArrayList<String> ingredients) {

    }

    void getRecipe(ArrayList<String> instructions) {

    }
}