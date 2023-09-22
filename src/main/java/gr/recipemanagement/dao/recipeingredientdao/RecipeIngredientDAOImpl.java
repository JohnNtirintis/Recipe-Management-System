package gr.recipemanagement.dao.recipeingredientdao;

import gr.recipemanagement.service.exceptions.IngredientNotFoundDAOException;
import gr.recipemanagement.service.exceptions.RecipeNotFoundDAOException;
import gr.recipemanagement.service.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author Ntirintis John
 */
public class RecipeIngredientDAOImpl implements RecipeIngredientDAO{

    @Override
    public void linkRecipeAndIngredient(int recipeId, int ingredientId) throws RecipeNotFoundDAOException{
        String sql = "INSERT INTO RECIPES_INGREDIENTS (RECIPEID, INGREDIENTID) VALUES (?,?)";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)){

            ps.setInt(1, recipeId);
            ps.setInt(2, ingredientId);

            ps.executeUpdate();
        }catch (SQLException e){
            throw new RecipeNotFoundDAOException("SQL Error in RecipeID: " + recipeId + " and IngredientID: " + ingredientId + " correlation "  );
        }
    }
}
