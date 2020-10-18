import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class JsonReader {
    private static ObjectMapper mapper = new ObjectMapper();
    private static ArrayList<String> ingredients = new ArrayList<String>();
    private static ArrayList<String> instructions = new ArrayList<String>();
    private static String recipesFilePath = "recipeBook.json";

    public JsonReader(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    /**
     * Reads the different recipes from the Json File and returns them as an ArrayList of Recipes
     *
     * @param book
     */
    public static void createRecipe(RecipeBook book) {
        ArrayList<String> ingredients = new ArrayList<String>();
        ArrayList<String> instructions = new ArrayList<String>();

        Scanner input = new Scanner(System.in);
        System.out.println("\nWhat is the name of this recipe?: ");
        System.out.print("Enter your input: ");
        String name = input.nextLine();

        Scanner descriptionInput = new Scanner(System.in);
        System.out.println("\nPlease enter a short description?: ");
        System.out.print("Enter your input: ");
        String description = descriptionInput.nextLine();
        Boolean cont = true;

        //add ingredients
        while (cont) {
            Scanner ingredientInput = new Scanner(System.in);
            System.out.println("\nAdd an ingredient: ");
            System.out.print("Enter your input: ");
            String ingredient = ingredientInput.nextLine();
            ingredients.add(ingredient);

            while (true) {
                Scanner inputCont = new Scanner(System.in);
                System.out.println("\nAre you going to add another? (1) Yes or (2) No: ");
                System.out.print("Enter your input: ");
                int inputContinue1 = 0;

                try {
                    inputContinue1 = inputCont.nextInt();
                }

                catch(InputMismatchException e){
                    ;
                }

                if (inputContinue1 == 1 || inputContinue1 == 2) {
                    cont = (inputContinue1 == 2) ? false : true;
                    break;
                } else {
                    System.out.println("Invalid input. Type 1 for  Yes or 2 for No.");
                    continue;
                }
            }

        }

        //add instructions
        cont = true;
        while (cont) {
            Scanner input3 = new Scanner(System.in);
            System.out.println("\nPlease add new step to the instructions: ");
            System.out.print("Enter your input: ");
            instructions.add(input3.nextLine());

            while (true) {
                Scanner inputCont2 = new Scanner(System.in);
                System.out.println("\nAre you going to update another step? (1) Yes or (2) No: ");
                System.out.print("Enter your input: ");
                int inputContinue2 = 0;
                
                try {
                    inputContinue2 = inputCont2.nextInt();
                }

                catch(InputMismatchException e){
                    ;
                }
                
                if (inputContinue2 == 2 || inputContinue2 == 1) {
                    cont = (inputContinue2 == 2) ? false : true;
                    break;
                } else {
                    System.out.println("Invalid input. Type 1 for  Yes or 2 for No.");
                    continue;
                }
            }
        }

        /**
         Updating recipebook and Json file
         * */

        try {
            Recipe newRecipe = new Recipe(name, description, ingredients, instructions);
            book.addRecipe(newRecipe);
            addToJsonFile(book.getRecipeBook());
            System.out.println("RECIPE CREATED SUCCESSFULLY!");
        }

        catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads the different recipes from the Json File and returns them as an ArrayList of Recipes
     *
     * @param filePath
     * @return the Recipes as an arrayList
     */
    public static ArrayList<Recipe> getRecipesFromJsonFile(String filePath) {
        ArrayList<Recipe> recipes = new ArrayList<>();
        File recipeFile = new File(filePath);
        if (recipeFile.length() == 0) {
            return recipes;
        }

        try {
            JSONParser parser = new JSONParser();
            JSONArray a = (JSONArray) parser.parse(new FileReader(filePath));
            for (Object o : a) {
                JSONObject recipe = (JSONObject) o;
                String name = (String) recipe.get("name");
                String description = (String) recipe.get("description");
                ArrayList<String> ingredients = (ArrayList<String>) recipe.get("ingredients");
                ArrayList<String> instructions = (ArrayList<String>) recipe.get("instructions");
                recipes.add(new Recipe(name, description, ingredients, instructions));
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return recipes;
    }

    /**
     * Formats and adds the Recipe arrayList to the Json File
     *
     * @param recipes
     */
    public static void addToJsonFile(ArrayList<Recipe> recipes) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(new File(recipesFilePath), recipes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Enables the user to select a specific recipe from a list of recipes returned from search
     *
     * @param foundRecipes
     * @return selected recipe
     */
    public static Recipe selectSpecificRecipe(ArrayList<Recipe> foundRecipes){
        StringBuilder found = new StringBuilder();
        Scanner scanner = new Scanner(System.in);
        int n = foundRecipes.size();
        Recipe selected = null;

        for (int i = 0; i < n; i++) {
            found.append(String.valueOf(i + 1) + ". " + foundRecipes.get(i).getName() + "\n");
        }

        found.append(String.valueOf(n + 1) + ". " + "back to main menu.");

        System.out.println(found);
        System.out.print("Enter your input: ");

        int choice = 0;
        try {   //input validation
            choice = scanner.nextInt();
        }

        catch(InputMismatchException e){
            ;
        }

        if (choice > n + 1){    //user choice out of bounds
            System.out.println("Please enter a valid input.");
            return selectSpecificRecipe(foundRecipes);
        }

        if (choice <= n) selected = foundRecipes.get(choice - 1);

        return selected;
    }

    /**
     * browse all recipes available and choose one
     *
     * @param book
     * @return selected recipe
     */
    public static Recipe browseRecipes(RecipeBook book){
        System.out.println("The following recipes are available. Please enter the number corresponding to a recipe to choose the one you want.");
        ArrayList<Recipe> recipeBook = book.getRecipeBook();

        return selectSpecificRecipe(recipeBook);
    }

    /**
     * search recipe from recipe book
     *
     * @param recipeBook
     * @param  recipeName
     * @return found recipe
     */
    public static Recipe searchRecipeBook(RecipeBook recipeBook, String recipeName) throws IOException {
        Recipe found = null;

        if (mapper == null) {
            System.out.println("No such info");
        } else {
            try {
                ArrayList<Recipe> foundRecipes = recipeBook.searchRecipe(recipeName);

                if (foundRecipes.size() <= 0) {
                    System.out.println("Sorry... no recipes with the name " + recipeName +  " was found. Please try again!");
                } else if (foundRecipes.size() == 1) {
                    found = foundRecipes.get(0);
                    System.out.println("\nThe recipe was found!");
                    System.out.println("Recipe name: " + found.getName());
                } else {
                    System.out.println("The following recipes were found. Which recipe did you mean?: \n");
                    found = selectSpecificRecipe(foundRecipes);
                }
                //System.out.println(mapper.writeValueAsString(book.getRecipe(filePath)));
            } catch (IndexOutOfBoundsException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return found;
    }

    /**
     * explore selected recipe
     *
     * @param recipe
     */
    public static void exploreRecipe(Recipe recipe){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Would you like to explore the " + recipe.getName() + " recipe?\n (1) read entire recipe\n (2) step through instructions\n (3) back to main menu");
        System.out.print("Enter your input: ");
        int choice = 0;
        int count = 0;
        try {
            choice = scanner.nextInt();
        }

        catch(InputMismatchException e){
            ;
        }

        switch (choice){
            case 1: //explore entire recipe
                recipe.readEntireInstruction();
                break;

            case 2: //step through recipe step by step
                System.out.println("\nINSTRUCTIONS: ");
                recipe.readInstructionStep(count);
                count++;
                while (true) {
                    Scanner userInput = new Scanner(System.in);
                    System.out.println("\nDo you want to see the next instruction? (1) yes (2) no");
                    System.out.print("Enter your input: ");
                    try{
                        choice = userInput.nextInt();
                    }

                    catch(InputMismatchException e){
                        choice = 0;
                    }


                    if (choice == 1) {

                        if (count >= recipe.getInstructions().size()){
                            System.out.println("END OF RECIPE INSTRUCTIONS");
                            break;
                        }
                        
                        recipe.readInstructionStep(count);
                        count++;

                    } else if (choice == 2){
                        break;
                    }

                    else{
                        System.out.println("Please enter a valid input.");
                    }
                }

                break;

            case 3:
                break;

            default:
                System.out.println("Please enter a valid input");
                exploreRecipe(recipe);
        }
    }
}