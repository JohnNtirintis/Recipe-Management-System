package gr.recipemanagement.service.ingredientservice;

import gr.recipemanagement.dto.ingredientdto.IngredientInsertDTO;
import gr.recipemanagement.dto.ingredientdto.IngredientUpdateDTO;
import gr.recipemanagement.model.Ingredient;
import gr.recipemanagement.service.exceptions.IngredientNotFoundDAOException;

/**
 * @author Ntirintis John
 */
public interface IIngredientService {

    Ingredient insertIngredient(IngredientInsertDTO dto) throws IngredientNotFoundDAOException;
    Ingredient updateIngredient(IngredientUpdateDTO dto) throws IngredientNotFoundDAOException;
    void deleteById(int id) throws IngredientNotFoundDAOException;
    Ingredient getIngredientById(int id) throws IngredientNotFoundDAOException;
}
