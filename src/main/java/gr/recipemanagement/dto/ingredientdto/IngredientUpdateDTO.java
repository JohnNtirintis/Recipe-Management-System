package gr.recipemanagement.dto.ingredientdto;

import gr.recipemanagement.dto.BaseDTO;

/**
 * @author Ntirintis John
 */
public class IngredientUpdateDTO extends BaseDTO {

    private Integer id;
    private String ingredientName;
    private double quantity;
    private String quantityType;

    private IngredientUpdateDTO() {}

    private IngredientUpdateDTO(int id, String ingredientName, String quantityType, double quantity) {
        super.setId(id);
        this.ingredientName = ingredientName;
        this.quantityType = quantityType;
        this.quantity = quantity;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public String getQuantityType() {
        return quantityType;
    }

    public void setQuantityType(String quantityType) {
        this.quantityType = quantityType;
    }
}
