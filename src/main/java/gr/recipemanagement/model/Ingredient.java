package gr.recipemanagement.model;

/**
 * @author Ntirintis John
 */
public class Ingredient {

    private Integer id;
    private String ingredientName;
    private Double quantity;
    private String quantityType;

    public Ingredient() {}

    public Ingredient(String ingredientName, Double quantity, String quantityType){
        this.ingredientName = ingredientName;
        this.quantity = quantity;
        this.quantityType = quantityType;
    }

    public Ingredient(Integer id, String ingredientName, Double quantity, String quantityType){
        this.id = id;
        this.ingredientName = ingredientName;
        this.quantity = quantity;
        this.quantityType = quantityType;
    }

    public Ingredient(String ingredientName){
        this.ingredientName = ingredientName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
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

    @Override
    public String toString() {
        return "Ingredient{" +
                "id=" + id +
                ", ingredientName='" + ingredientName + '\'' +
                ", quantity=" + quantity +
                ", quantityType='" + quantityType + '\'' +
                '}';
    }
}
