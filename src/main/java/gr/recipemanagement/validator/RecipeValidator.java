package gr.recipemanagement.validator;

import gr.recipemanagement.dto.recipedto.RecipeInsertDTO;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Ntirintis John
 */
public class RecipeValidator {

    private RecipeValidator() {}

    public static Map<String, String> validate(RecipeInsertDTO dto){
        Map<String, String> errors = new HashMap<>();

        if (dto.getRecipeName() == null || dto.getRecipeName().trim().isEmpty() ||
                dto.getRecipeName().trim().length() < 3 || dto.getRecipeName().trim().length() >= 45) {
            errors.put("RecipeName", "Size or Null");
        }

        if (dto.getInstructions() == null || dto.getInstructions().trim().isEmpty() ||
                dto.getInstructions().trim().length() == 0 || dto.getInstructions().trim().length() >= 1000) {
            errors.put("Instructions", "Size or Null");
        }

        return errors;
    }
}
