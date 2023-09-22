package gr.recipemanagement.dto.recipedto;

import gr.recipemanagement.dto.BaseDTO;
import gr.recipemanagement.model.Ingredient;
import gr.recipemanagement.service.exceptions.IngredientNotFoundDAOException;

/**
 * @author Ntirintis John
 */
public class RecipeInsertDTO extends BaseDTO {
    // We take the id from the base DTO class

    private String ingredientName;
    private String recipeName;
    private String instructions;
    private Ingredient ingredients;
    private double cookingTime;

    public RecipeInsertDTO() {}

    public RecipeInsertDTO(String recipeName, String instructions, double cookingTime){
        setRecipeName(recipeName);
        this.instructions = instructions.trim();
        this.cookingTime = cookingTime;
    }

    public Ingredient getIngredients(){
        return ingredients;
    }

    public void setIngredients(Ingredient ingredients){
        this.ingredients = ingredients;
    }

//    public void setIngredientByName(String ingredientName){
//
//    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = (recipeName == null) ? null : recipeName.trim(); ;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public double getCookingTime() {
        return cookingTime;
    }

    public void setCookingTime(double cookingTime) {
        this.cookingTime = cookingTime;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }
}
