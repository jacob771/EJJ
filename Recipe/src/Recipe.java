import java.util.*;

public class Recipe {

    /**
     * Vaiables
     * */

    private ArrayList<Object> Recipe = new ArrayList<>();
    private String name;
    private String description;
    private ArrayList<String> ingredients;
    private ArrayList<String> instructions;

    /**
     * Constructor
     * */

    Recipe (String name, String description, ArrayList<String> ingredients, ArrayList<String> instructions) {
        this.name = name;
        this.description = description;
        this.ingredients = ingredients;
        this.instructions = instructions;
    }

    /**
     * Getter
     * */

    ArrayList<Object> getRecipe() {
        return Recipe;
    }

    /**
     * Setter
     * */

    void setRecipe(String name, String description, ArrayList<String> ingredients, ArrayList<String> instructions) {
        Recipe recipe = new Recipe(name, description, ingredients, instructions);
    }
}