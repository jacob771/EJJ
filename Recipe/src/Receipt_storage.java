import java.util.*;
import java.io.*;

public class Receipt_storage {
    private ArrayList<String> storage = new ArrayList<>();
    private String name;
    private String description;
    private String[] ingredients;
    private String[] instructions;

    private List<String> Receipt(String name, String description, String[] ingredients, String[] instructions) {
        this.name = name;
        this.description = description;
        this.ingredients = ingredients;
        this.instructions = instructions;

        return storage;
    }

    private void push_receipt() {

    }

    private String pull_receipt(String name) {
        if (storage.contains(name)) {
            int index = storage.indexOf(name);
            return storage.get(index);
        } else {
            return null;
        }
    }

    public ArrayList<String> getStorage() {
        return storage;
    }
}
