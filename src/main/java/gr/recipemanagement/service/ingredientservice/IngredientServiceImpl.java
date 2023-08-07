package gr.recipemanagement.service.ingredientservice;

import gr.recipemanagement.dao.ingredientdao.IIngredientDAO;
import gr.recipemanagement.dto.ingredientdto.IngredientInsertDTO;
import gr.recipemanagement.dto.ingredientdto.IngredientUpdateDTO;
import gr.recipemanagement.factory.StorageFactory;
import gr.recipemanagement.model.Ingredient;
import gr.recipemanagement.service.exceptions.IngredientNotFoundDAOException;

/**
 * @author Ntirintis John
 */
public class IngredientServiceImpl implements IIngredientService {

    private IIngredientDAO ingredientDAO;

    public IngredientServiceImpl(StorageFactory factory){
        this.ingredientDAO = factory.createIngredientDAO();
    }

    @Override
    public Ingredient insertIngredient(IngredientInsertDTO dto) throws IngredientNotFoundDAOException {
        if(dto == null) return null;

        Ingredient ingredient;

        try {
            ingredient = map(dto);
            return ingredientDAO.insert(ingredient);
        } catch (IngredientNotFoundDAOException e){
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public Ingredient updateIngredient(IngredientUpdateDTO dto) throws IngredientNotFoundDAOException {
        if(dto == null) return null;

        Ingredient ingredient;

        try {
            ingredient = map(dto);
            if(ingredientDAO.getById(ingredient.getId()) == null){
                throw new IngredientNotFoundDAOException(ingredient);
            }
            return ingredientDAO.update(ingredient);
        } catch (IngredientNotFoundDAOException e){
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public void deleteById(int id) throws IngredientNotFoundDAOException {

        Ingredient ingredient;

        try {
            ingredient = ingredientDAO.getById(id);
            if(ingredient.getId() == null){
                throw new IngredientNotFoundDAOException("Deletion Error! Ingredient with id: " + id + " wasn't found!");
            }
            ingredientDAO.delete(id);
        } catch (IngredientNotFoundDAOException e){
            e.printStackTrace();
            System.err.println("Error! Couldn't delete ingredient with id: " +  id );
            throw e;
        }
    }

    @Override
    public Ingredient getIngredientById(int id) throws IngredientNotFoundDAOException {

        Ingredient ingredient;

        try {
            ingredient = ingredientDAO.getById(id);
            if(ingredient == null){
                throw new IngredientNotFoundDAOException("Error: ingredient with id: " + id + " wasn't found!");
            }

            return ingredient;
        } catch (IngredientNotFoundDAOException e){
            e.printStackTrace();
            throw e;
        }
    }

    private Ingredient map(IngredientInsertDTO dto){
        return new Ingredient(null, dto.getIngredientName(), dto.getQuantity(), dto.getQuantityType());
    }

    private Ingredient map(IngredientUpdateDTO dto){
        return new Ingredient(dto.getId(), dto.getIngredientName(), dto.getQuantity(), dto.getQuantityType());
    }
}
