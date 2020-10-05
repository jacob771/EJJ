import java.util.*;
import java.io.*;

public class Recipe {
    private ArrayList<Object> Recipe = new ArrayList<>();
    private String name;
    private String description;
    private ArrayList<String> ingredients;
    private ArrayList<String> instructions;

    Recipe(String name, String description, ArrayList<String> ingredients, ArrayList<String> instructions) {
        this.name = name;
        this.description = description;
        this.ingredients = ingredients;
        this.instructions = instructions;
    }
}
