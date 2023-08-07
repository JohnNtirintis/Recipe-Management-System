package gr.recipemanagement.model;

import java.util.List;

/**
 * @author Ntirintis John
 */
public class Recipe {

    private Integer id;
    private String recipeName;
    private String instructions;
    private double cookingTime;
    private List<Ingredient> ingredients;

    public Recipe() {}

    public Recipe(String recipeName, String instructions, double cookingTime) {
        this.recipeName = recipeName;
        this.instructions = instructions;
        this.cookingTime = cookingTime;
    }

    public Recipe(Integer id, String recipeName, String instructions, double cookingTime) {
        this.id = id;
        this.recipeName = recipeName;
        this.instructions = instructions;
        this.cookingTime = cookingTime;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public Integer getId() { return id;}

    public void setId(int id){
        this.id = id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Recipe{ " +
                "id= " + id +
                ", recipeName='" + recipeName + '\'' +
                ", instructions='" + instructions + '\'' +
                ", cookingTime=" + cookingTime +
                '}';
    }
}
