package gr.recipemanagement.dao.ingredientdao;

import gr.recipemanagement.model.Ingredient;
import gr.recipemanagement.service.exceptions.IngredientNotFoundDAOException;

/**
 * @author Ntirintis John
 */
public interface IIngredientDAO {

    Ingredient insert(Ingredient ingredient) throws IngredientNotFoundDAOException;
    int insert(String ingredientName) throws IngredientNotFoundDAOException;
    Ingredient update(Ingredient ingredient) throws IngredientNotFoundDAOException;
    void delete(int id) throws  IngredientNotFoundDAOException;
    Ingredient getById(int id) throws IngredientNotFoundDAOException;
    Ingredient getByName(String ingredientName) throws IngredientNotFoundDAOException;
}
