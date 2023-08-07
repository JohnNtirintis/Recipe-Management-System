package gr.recipemanagement.factory;

import gr.recipemanagement.dao.ingredientdao.IIngredientDAO;
import gr.recipemanagement.dao.recipedao.IRecipeDAO;

/**
 * @author Ntirintis John
 */
public interface StorageFactory {

    IRecipeDAO createRecipeDAO();
    IIngredientDAO createIngredientDAO();
}
