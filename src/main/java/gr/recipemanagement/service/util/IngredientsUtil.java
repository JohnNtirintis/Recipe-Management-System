package gr.recipemanagement.service.util;

import gr.recipemanagement.model.Ingredient;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility class to fetch ingredients from a selected recipe
 *
 * @author Ntirintis John
 */
public class IngredientsUtil {

    public static List<Ingredient> fetchIngredientsForRecipe(int recipeId){
        String sql = "SELECT i.ingredientname, i.quantity, i.quantitytype " +
                "FROM ingredients i " +
                "INNER JOIN recipes_ingredients ri ON i.ingredientid = ri.ingredientid " +
                "WHERE ri.recipeid = ?";
        ResultSet rs = null;
        List<Ingredient> ingredients = new ArrayList<>();
        Ingredient ingredient = null;

        try (Connection connection = DBUtil.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)){

            ps.setInt(1, recipeId);
            rs = ps.executeQuery();

            // While there is a next ingredient, add it to the list of ingredients
            while(rs.next()){
                ingredient = new Ingredient(rs.getInt("INGREDIENTID"), rs.getString("INGREDIENTNAME"), rs.getDouble("QUANTITY"), rs.getString("QUANTITYTYPE"));
                ingredients.add(ingredient);
            }
        } catch (SQLException e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error! Ingredient with id " + recipeId + " not found.", JOptionPane.ERROR_MESSAGE);
            // Return empty list in case of error
            return new ArrayList<>();
        } finally {
            // close result statement
            try {
                if (rs != null){
                    rs.close();
                }
            } catch (SQLException e1){
                e1.printStackTrace();
            }
        }
        return ingredients;
    }

    // Validating information
    public static boolean validateIngredients(List<Ingredient> ingredients){

        for (Ingredient ingredient : ingredients){
            if(ingredient.getIngredientName() == null || ingredient.getIngredientName().trim().isEmpty()){
                return false;
            }
            if (ingredient.getQuantity() == null || ingredient.getQuantity() <= 0) {
                return false;
            }
        }

        return true;
    }

    public static String[] ingredientsToString(List<Ingredient> ingredients){
        String[] ingredientNames = new String[ingredients.size()];

        for(int i = 0; i < ingredients.size(); i++){
            ingredientNames[i] = ingredients.get(i).getIngredientName();
        }

        return ingredientNames;
    }
}
