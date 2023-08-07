package gr.recipemanagement.dto.ingredientdto;

import gr.recipemanagement.dto.BaseDTO;

/**
 * @author Ntirintis John
 */
public class IngredientInsertDTO extends BaseDTO {

    private String ingredientName;
    private Double quantity;
    private String quantityType;

    private IngredientInsertDTO() {}

    private IngredientInsertDTO(String ingredientName, String quantityType, Double quantity) {
        setIngredientName(ingredientName);
        this.quantityType = quantityType;
        this.quantity = quantity;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = (ingredientName == null ) ? null : ingredientName.trim();
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public String getQuantityType() {
        return quantityType;
    }

    public void setQuantityType(String quantityType) {
        this.quantityType = quantityType;
    }
}
