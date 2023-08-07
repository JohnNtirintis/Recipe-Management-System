package gr.recipemanagement.factory;

import gr.recipemanagement.dao.ingredientdao.IIngredientDAO;
import gr.recipemanagement.dao.ingredientdao.IngredientDAOImpl;
import gr.recipemanagement.dao.recipedao.IRecipeDAO;
import gr.recipemanagement.dao.recipedao.RecipeDAOImpl;

/**
 * @author Ntirintis John
 */
public class SQLStorageFactory implements StorageFactory {


    @Override
    public IRecipeDAO createRecipeDAO() {
        return new RecipeDAOImpl();
    }

    @Override
    public IIngredientDAO createIngredientDAO() {
        return new IngredientDAOImpl();
    }
}
