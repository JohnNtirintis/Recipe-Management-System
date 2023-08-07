package gr.recipemanagement.service.exceptions;

import gr.recipemanagement.model.Recipe;

/**
 * @author Ntirintis John
 */
public class RecipeNotFoundDAOException extends Exception {
    private static final long serialVersionUID = 123456L;

    public RecipeNotFoundDAOException(Recipe recipe){
        super("Recipe with id: " + recipe.getId() + " wasn't found!");
    }

    public RecipeNotFoundDAOException(String s){
        super(s);
    }
}
