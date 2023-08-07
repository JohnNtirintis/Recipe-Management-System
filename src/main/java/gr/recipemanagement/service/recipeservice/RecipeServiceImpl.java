package gr.recipemanagement.service.recipeservice;

import gr.recipemanagement.dao.recipedao.IRecipeDAO;
import gr.recipemanagement.dto.recipedto.RecipeInsertDTO;
import gr.recipemanagement.dto.recipedto.RecipeUpdateDTO;
import gr.recipemanagement.factory.StorageFactory;
import gr.recipemanagement.model.Ingredient;
import gr.recipemanagement.model.Recipe;
import gr.recipemanagement.service.exceptions.RecipeNotFoundDAOException;

import java.util.List;

/**
 * @author Ntirintis John
 */
public class RecipeServiceImpl implements IRecipeService {

    private IRecipeDAO recipeDAO;

    public RecipeServiceImpl(StorageFactory factory) {

        this.recipeDAO = factory.createRecipeDAO();
    }

    @Override
    public Recipe insertRecipe(RecipeInsertDTO dto) throws RecipeNotFoundDAOException {
        if(dto == null) return null;

        Recipe recipe;

        try {
            recipe = map(dto);
            return recipeDAO.insert(recipe);
        } catch (RecipeNotFoundDAOException e){
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public Recipe updateRecipe(RecipeUpdateDTO dto) throws RecipeNotFoundDAOException {
        if(dto == null) return null;

        Recipe recipe;

        try {
            recipe = map(dto);
            if(recipeDAO.getById(recipe.getId()) == null){
                throw new RecipeNotFoundDAOException(recipe);
            }
            return recipeDAO.update(recipe);
        } catch (RecipeNotFoundDAOException e){
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public void deleteRecipe(int id) throws RecipeNotFoundDAOException {

        try {
            Recipe recipe = recipeDAO.getById(id);

            if(recipe == null){
                throw new RecipeNotFoundDAOException("Deletion Error! Recipe with id: " + id + " wasn't found!");
            }

            recipeDAO.delete(id);
        } catch (RecipeNotFoundDAOException e){
            e.printStackTrace();
            System.err.println("Error! Couldn't delete recipe with id: " +  id );
            throw e;
        }
    }

    @Override
    public Recipe getRecipeById(int id) throws RecipeNotFoundDAOException {
        Recipe recipe;

        try {
            recipe = recipeDAO.getById(id);
            if(recipe == null){
                throw new RecipeNotFoundDAOException("Error: recipe with id: " + id + " wasn't found!");
            }

            return recipe;
        } catch (RecipeNotFoundDAOException e){
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public List<Recipe> getByFirstLetter(String firstLetter) throws RecipeNotFoundDAOException {
        List<Recipe> recipes;

        try {
            recipes = recipeDAO.getRecipeByFirstLetter(firstLetter);
            return recipes;
        } catch (RecipeNotFoundDAOException e){
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public Recipe getRecipeByName(String recipeName) throws RecipeNotFoundDAOException {
        Recipe recipe;

        try {
            recipe =  recipeDAO.getRecipeByName(recipeName);
            if(recipe == null){
                throw new RecipeNotFoundDAOException("Error! Recipe with the name: " + recipeName + " wasn't found.");
            }
            return recipe;
        } catch (RecipeNotFoundDAOException e){
            throw e;
        }
    }

    @Override
    public List<Ingredient> getRecipeIngredients(int recipeId) throws RecipeNotFoundDAOException {
        List<Ingredient> ingredients;

        try {
            ingredients = recipeDAO.getRecipeIngredients(recipeId);
            return ingredients;
        } catch (RecipeNotFoundDAOException e){
            e.printStackTrace();
            throw e;
        }
    }

    private Recipe map(RecipeInsertDTO dto){
        return new Recipe(null, dto.getRecipeName(), dto.getInstructions(), dto.getCookingTime());
    }

    private Recipe map(RecipeUpdateDTO dto){
        return new Recipe(dto.getId(), dto.getRecipeName(), dto.getInstructions(), dto.getCookingTime());
    }
}
