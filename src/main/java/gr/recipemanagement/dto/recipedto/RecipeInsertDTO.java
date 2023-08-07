package gr.recipemanagement.dto.recipedto;

import gr.recipemanagement.dto.BaseDTO;

/**
 * @author Ntirintis John
 */
public class RecipeInsertDTO extends BaseDTO {
    // We take the id from the base DTO class

    private String recipeName;
    private String instructions;
    private double cookingTime;

    public RecipeInsertDTO() {}

    public RecipeInsertDTO(String recipeName, String instructions, double cookingTime){
        setRecipeName(recipeName);
        this.instructions = instructions.trim();
        this.cookingTime = cookingTime;
    }

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
}
