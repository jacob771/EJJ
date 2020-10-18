//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
//import org.json.simple.parser.ParseException;

import org.apache.commons.lang3.StringUtils;

import java.util.*;

public class RecipeBook {
    ArrayList<Recipe> recipeBook;

    public RecipeBook (ArrayList<Recipe> recipeBook) {
    	this.recipeBook = recipeBook;
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
    public void addRecipe (Recipe recipe) {
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
     * 		Store all recipes that have the lowest score in an ArrayList and return.
     * 
     * @param recipe name query
     * @return ArrayList of recipes that have the lowest Levenshtein Distance score
     */
    ArrayList<Recipe> searchRecipe(String name) {
    	HashMap<Recipe, Integer> scoresMap = new HashMap<Recipe, Integer>();
    	ArrayList<Recipe> foundRecipes = new ArrayList<Recipe>();
    	ArrayList<Recipe> sortedFoundRecipes = new ArrayList<Recipe>();
    	
    	for (int i = 0; i < recipeBook.size(); i++) {
    		if (recipeBook.get(i).getName().charAt(0) == name.charAt(0) || recipeBook.get(i).getName().contains(name)) {
    			foundRecipes.add(recipeBook.get(i));
    		}
    	}
    	
    	for (int i = 0; i < foundRecipes.size(); i++) {
    		int distance = StringUtils.getLevenshteinDistance(foundRecipes.get(i).getName(), name);
    		scoresMap.put(foundRecipes.get(i), distance);
    	}
    	List<Integer> mapValues = new ArrayList<>(scoresMap.values());
    	Collections.sort(mapValues);
    	
    	//get iterator for scoresMap 
        Iterator scoresIterator = scoresMap.entrySet().iterator(); 
        
        //iterate thru scoresMap
        while (scoresIterator.hasNext()) { 
            Map.Entry<Recipe,Integer> mapElement = (Map.Entry<Recipe,Integer>)scoresIterator.next(); 
            //System.out.println(mapElement.getKey().getName() + ": " + mapElement.getValue());
            sortedFoundRecipes.add(mapElement.getKey());
        }
        return sortedFoundRecipes;
    }
}