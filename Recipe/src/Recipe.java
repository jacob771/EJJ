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
     * Construct a new Recipe object with given parameters
     * @param name
     * @param description
     * @param ingredients
     * @param instructions
     */
    Recipe (String name, String description,
            ArrayList<String> ingredients, ArrayList<String> instructions) {
        this.name = name;
        this.description = description;
        this.ingredients = ingredients;
        this.instructions = instructions;
    }

    /**
     * Null Recipe constructor
     */
    Recipe()
    {
        this.name = null;
        this.description = null;
        this.ingredients = null;
        this.instructions = null;
    }

    /**
     * Set a recipe with the given constraints
     * @param name
     * @param description
     * @param ingredients
     * @param instructions
     */
    public void setRecipe(String name, String description,
                          ArrayList<String> ingredients, ArrayList<String> instructions)
    {
        setName(name);
        setDescription(description);
        setIngredients(ingredients);
        setInstructions(instructions);
    }

    /**
     * Set name for the recipe
     * @param name
     */
    public void setName(String name) { this.name = name; }

    /**
     * get the name of a selected recipe
     * @return
     */
    public String getName() { return this.name; }

    /**
     * Set the recipe description
     * @param description
     */
    public void setDescription(String description) { this.description = description; }

    /**
     * Get the descirption from the selected recipe
     * @return
     */
    public String getDescription() { return this.description; }

    /**
     * Set the arrayList for the ingredients
     * @param ingredients
     */
    public void setIngredients(ArrayList<String> ingredients) { this.ingredients = ingredients; }

    /**
     * Get the arrayList containing the ingredients
     * @return
     */
    public ArrayList<String> getIngredients() { return this.ingredients; }

    /**
     * Set the array list containing the instructions
     * @param instructions
     */
    public void setInstructions(ArrayList<String> instructions) { this.instructions = instructions; }

    /**
     * Get the array list containing the instructions
     * @return
     */
    public ArrayList<String> getInstructions() { return this.instructions; }
}