package gr.recipemanagement.validator;

import gr.recipemanagement.dto.ingredientdto.IngredientInsertDTO;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Ntirintis John
 */
public class IngredientValidator {

    private IngredientValidator() {}

    public static Map<String, String> validate(IngredientInsertDTO dto) {
        Map<String, String> errors = new HashMap<>();

        if (dto.getIngredientName() == null || dto.getIngredientName().trim().isEmpty() ||
                dto.getIngredientName().trim().length() < 3 || dto.getIngredientName().trim().length() >= 45) {
            errors.put("IngredientName", "Size or Null");
        }

        if (dto.getQuantity() == null || dto.getQuantity() < 0) {
            errors.put("Quantity", "Negative or Null");
        }

        return errors;
    }
}
