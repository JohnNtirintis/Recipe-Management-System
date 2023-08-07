package gr.recipemanagement.service.exceptions;

import gr.recipemanagement.model.Ingredient;

/**
 * @author Ntirintis John
 */
public class IngredientNotFoundDAOException extends Exception {

    private static final long serialVersionUID = 123465L;

    public IngredientNotFoundDAOException(Ingredient ingredient){
        super("Ingredient with id: " + ingredient.getId() + " wasn't found!");
    }

    public IngredientNotFoundDAOException(String s){
        super(s);
    }
}
