import java.util.*;
import java.io.*;

public class RecipeBook {
    public ArrayList<Recipe> storage = new ArrayList<>();

    private void setStorage(String name, String description, ArrayList <String> ingredients, ArrayList<String> instructions) {
        Recipe recipe = new Recipe(name, description, ingredients, instructions);
        storage.add(recipe);
    }

    private ArrayList<Recipe> getStorage () {
        return storage;
    }
}