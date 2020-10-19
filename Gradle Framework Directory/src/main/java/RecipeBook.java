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
     * @param recipe
     */
    public void addRecipe (Recipe recipe) {
        recipeBook.add(recipe);
    }

    /**
     * Searches for recipe by:
     * 		Calculating all Levenshtein Distance scores for recipe names.
     * 		The lower the score, the closest the name is to the recipe name query.
     * 		Store scores in HashMap and reorder HashMap from lowest to highest.
     * 		Store all recipes that have the lowest score in an ArrayList and return.
     *
     * @param name
     * @return ArrayList of recipes that have the lowest Levenshtein Distance score
     */
    ArrayList<Recipe> searchRecipe(String name) {
        HashMap<Recipe, Integer> scoresMap = new HashMap<Recipe, Integer>();
        ArrayList<Recipe> foundRecipes = new ArrayList<Recipe>();
        ArrayList<Recipe> sortedFoundRecipes = new ArrayList<Recipe>();
        for (int i = 0; i < recipeBook.size(); i++) {
            String recipeName = recipeBook.get(i).getName().toLowerCase();
            if (recipeName.charAt(0) == name.charAt(0) || recipeName.contains(name)) {
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

    public void sort() {
        Collections.sort(this.recipeBook);
    }
}