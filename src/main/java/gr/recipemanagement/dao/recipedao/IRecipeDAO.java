package gr.recipemanagement.dao.recipedao;

import gr.recipemanagement.dto.recipedto.RecipeInsertDTO;
import gr.recipemanagement.model.Ingredient;
import gr.recipemanagement.model.Recipe;
import gr.recipemanagement.service.exceptions.RecipeInsertException;
import gr.recipemanagement.service.exceptions.RecipeNotFoundDAOException;

import java.util.List;
import java.util.Map;

/**
 * @author Ntirintis John
 */
public interface IRecipeDAO {

    Recipe insert(Recipe recipe) throws RecipeNotFoundDAOException;

    Recipe insert(RecipeInsertDTO dto) throws RecipeInsertException;


    Recipe update(Recipe recipe) throws RecipeNotFoundDAOException;
    void delete(int id) throws RecipeNotFoundDAOException;
    List<Recipe> getRecipeByFirstLetter(String firstLetter) throws RecipeNotFoundDAOException;
    Recipe getById(int id) throws RecipeNotFoundDAOException;
    List<Ingredient> getRecipeIngredients(int recipeId) throws RecipeNotFoundDAOException;
    Recipe getRecipeByName(String recipeName) throws RecipeNotFoundDAOException;
    List<String> getRecipeNames() throws RecipeNotFoundDAOException;
    String getRecipeDetails(String recipeName) throws RecipeNotFoundDAOException;
}
