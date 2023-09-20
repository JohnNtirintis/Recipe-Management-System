package gr.recipemanagement.dto.recipedto;

import gr.recipemanagement.dto.BaseDTO;
import gr.recipemanagement.model.Ingredient;

/**
 * @author Ntirintis John
 */
public class RecipeUpdateDTO extends BaseDTO {

    private Integer id;
    private String recipeName;
    private Ingredient ingredients;
    private String instructions;
    private double cookingTime;

    public RecipeUpdateDTO() {}

    public RecipeUpdateDTO(int id, String recipeName, String instructions, double cookingTime){
        super.setId(id);
        this.recipeName = recipeName;
        this.instructions = instructions;
        this.cookingTime = cookingTime;
    }

    public Ingredient getIngredients(){
        return ingredients;
    }

    public void setIngredients(Ingredient ingredients){
       this.ingredients = ingredients;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
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
}
