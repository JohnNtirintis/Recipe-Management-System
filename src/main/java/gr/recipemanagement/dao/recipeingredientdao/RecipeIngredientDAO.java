package gr.recipemanagement.dao.recipeingredientdao;

import gr.recipemanagement.service.exceptions.IngredientNotFoundDAOException;
import gr.recipemanagement.service.exceptions.RecipeNotFoundDAOException;

/**
 * @author Ntirintis John
 */
public interface RecipeIngredientDAO {

    void linkRecipeAndIngredient(int recipeId, int ingredientId) throws RecipeNotFoundDAOException;
}
