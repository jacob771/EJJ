//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
//import org.json.simple.parser.ParseException;

import java.util.*;

import org.apache.commons.lang3.StringUtils;

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
    
    /**
     * Searches for recipe by:
     * 		Calculating all Levenshtein Distance scores for recipe names.
     * 		The lower the score, the closest the name is to the recipe name query.
     * 		Store scores in HashMap and reorder HashMap from lowest to highest.
     * 
     * @param recipe name query
     * @return recipe with lowest Levenshtein Distance score
     */
    private Recipe searchRecipe(String name) {
    	HashMap<Integer, String> scoresMap = new HashMap<Integer, String>();

    	for (int i = 0; i < recipeBook.size(); i++) {
    		int distance = StringUtils.getLevenshteinDistance(recipeBook.get(i).getName(), name);
    		scoresMap.put(distance, recipeBook.get(i).getName());
    	}
    	List<Integer> mapKeys = new ArrayList<>(scoresMap.keySet());
    	Collections.sort(mapKeys);
    	if (mapKeys.get(0) == 0) {
    		//search is an exact match with search query
    		return getRecipe(scoresMap.get(mapKeys.get(0)));
    	} else { 
    		//search is a fuzzy result with search query
    		return getRecipe(scoresMap.get(mapKeys.get(0)));
    	}
    }
}