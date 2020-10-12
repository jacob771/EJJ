import java.util.*;

public class Recipe {

    /**
     * Vaiables
     * */

    private String name;
    private String description;
    private ArrayList<String> ingredients;
    private ArrayList<String> instructions;
    private ArrayList<String> testString;

    /**
     * Constructor
     * */

    Recipe (String name, String description,
            ArrayList<String> ingredients, ArrayList<String> instructions) {
        this.name = name;
        this.description = description;
        this.ingredients = ingredients;
        this.instructions = instructions;
    }

    Recipe()
    {
        this.name = null;
        this.description = null;
        this.ingredients = null;
        this.instructions = null;
    }

    public void setRecipe(String name, String description,
                          ArrayList<String> ingredients, ArrayList<String> instructions)
    {
        setName(name);
        setDescription(description);
        setIngredients(ingredients);
        setInstructions(instructions);
    }

    public void setName(String name) { this.name = name; }

    public String getName() { return this.name; }

    public void setDescription(String description) { this.description = description; }

    public String getDescription() { return this.description; }

    public void setIngredients(ArrayList<String> ingredients) { this.ingredients = ingredients; }

    public ArrayList<String> getIngredients() { return this.ingredients; }

    public void setInstructions(ArrayList<String> instructions) { this.instructions = instructions; }

    public ArrayList<String> getInstructions() { return this.instructions; }
}