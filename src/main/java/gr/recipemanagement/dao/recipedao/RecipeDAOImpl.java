package gr.recipemanagement.dao.recipedao;

import gr.recipemanagement.model.Ingredient;
import gr.recipemanagement.model.Recipe;
import gr.recipemanagement.service.exceptions.RecipeNotFoundDAOException;
import gr.recipemanagement.service.util.DBUtil;

import javax.swing.*;
import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Ntirintis John
 */
public class RecipeDAOImpl implements IRecipeDAO {

    @Override
    public Recipe insert(Recipe recipe) throws RecipeNotFoundDAOException {
        String sql = "INSERT INTO RECIPES (RECIPENAME, INSTRUCTIONS, COOKINGTIME) VALUES (?,?,?)";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)){

            String recipeName = recipe.getRecipeName();
            String instructions = recipe.getInstructions();
            double cookingTime = recipe.getCookingTime();

            ps.setString(1, recipeName);
            ps.setString(2, instructions);
            ps.setDouble(3, cookingTime);

            ps.executeUpdate();

            return recipe;
        } catch (SQLException e){
            e.printStackTrace();
            throw new RecipeNotFoundDAOException("SQL Error in Recipe Insertion " + recipe);
        }
    }

    @Override
    public Recipe update(Recipe recipe) throws RecipeNotFoundDAOException {
        String sql = "UPDATE RECIPES SET RECIPENAME = ?, INSTRUCTION = ?, COOKINGTIME = ? WHERE RECIPEID = ?";

        try(Connection connection = DBUtil.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)){

            int id = recipe.getId();
            String recipeName = recipe.getRecipeName();
            String instructions = recipe.getInstructions();
            double cookingTime = recipe.getCookingTime();

            ps.setString(1, recipeName);
            ps.setString(2, instructions);
            ps.setDouble(3, cookingTime);
            ps.setInt(4, id);

            ps.executeUpdate();

            return recipe;
        }catch (SQLException e){
            e.printStackTrace();
            throw new RecipeNotFoundDAOException("Error in updating recipe " + recipe);
        }
    }

    @Override
    public void delete(int id) throws RecipeNotFoundDAOException {
        String sql = "DELETE FROM RECIPES WHERE ID = ?";

        try(Connection connection = DBUtil.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)){

            ps.setInt(1, id);

            int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete recipe with id: " + id, "Warning!", JOptionPane.YES_NO_OPTION);
            if(response == JOptionPane.YES_NO_OPTION){
                int n = ps.executeUpdate();
                if(n >= 1){
                    JOptionPane.showMessageDialog(null, n + "row(s) affected", "Successful Deletion!", JOptionPane.PLAIN_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "No row(s) affected", "Failed Deletion!", JOptionPane.PLAIN_MESSAGE);
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
            throw new RecipeNotFoundDAOException("Error in deleting recipe with id: " + id);
        }
    }

    @Override
    public Recipe getById(int id) throws RecipeNotFoundDAOException {
        String sql = "SELECT * FROM RECIPES WHERE ID = ?";
        Recipe recipe = null;
        ResultSet rs = null;

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)){

            ps.setInt(1, id);
            rs = ps.executeQuery();

            if(rs.next()){
                recipe = new Recipe(rs.getInt("RECIPEID"), rs.getString("RECIPENAME"), rs.getString("INSTRUCTIONS"), rs.getDouble("COOKINGTIME"));
            }
        } catch (SQLException e){
            e.printStackTrace();
            throw new RecipeNotFoundDAOException("Error! Recipe with id " + id + " wasn't found!");
        } finally {
            try {
                if (rs != null) rs.close();
            } catch (SQLException e1){
                e1.printStackTrace();
            }
        }
        return recipe;
    }

    @Override
    public List<Recipe> getRecipeByFirstLetter(String firstLetter) throws RecipeNotFoundDAOException {

        String sql = "SELECT * FROM RECIPES WHERE RECIPENAME LIKE ?";
        List<Recipe> recipes = new ArrayList<>();
        ResultSet rs = null;

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)){

            ps.setString(1, firstLetter + "%");
            rs = ps.executeQuery();

            while(rs.next()){
                Recipe recipe = new Recipe(rs.getInt("RECIPEID"), rs.getString("RECIPENAME"),
                        rs.getString("INSTRUCTIONS"), rs.getDouble("COOKINGTIME"));
                recipes.add(recipe);
            }

        }  catch (SQLException e){
            e.printStackTrace();
        }

        if(recipes.isEmpty()){
            throw new RecipeNotFoundDAOException("No recipes found starting with the letter: " + firstLetter);
        }

        return recipes;
    }

    @Override
    public List<Ingredient> getRecipeIngredients(int recipeId) throws RecipeNotFoundDAOException {
        String sql = "SELECT i.ingredientname, i.quantity, i.quantitytpe FROM ingredients i INNER JOIN recipes_ingredients ri on i.ingredientid = ri.ingredientid WHERE ri.recipeid = ?";
        List<Ingredient> ingredients = new ArrayList<>();
        ResultSet rs = null;

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)){

            ps.setInt(1, recipeId);
            rs = ps.executeQuery();

            while(rs.next()){
                Ingredient ingredient = new Ingredient(
                        rs.getInt("ingredientid"),
                        rs.getString("ingredientname"),
                        rs.getDouble("quantity"),
                        rs.getString("quantitytype")
                );
                ingredients.add(ingredient);
            }

        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
            } catch (SQLException e1){
                e1.printStackTrace();
            }
        }

        return ingredients;
    }

    @Override
    public Recipe getRecipeByName(String recipeName) throws RecipeNotFoundDAOException {
        String sql = "SELECT * FROM RECIPES WHERE RECIPENAME = ?";
        Recipe recipe = null;
        ResultSet rs = null;

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)){

            ps.setString(1, recipeName);
            rs = ps.executeQuery();

            if(!rs.next()){
                throw new RecipeNotFoundDAOException("Error! Couldn't find recipe with name: " + recipeName);
            }

            recipe = new Recipe(rs.getInt("RECIPEID"), rs.getString("RECIPENAME"),
                    rs.getString("INSTRUCTIONS"), rs.getDouble("COOKINGTIME"));

            List<Ingredient> recipeIngredients = getRecipeIngredients(recipe.getId());
            recipe.setIngredients(recipeIngredients);

        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
            } catch (SQLException e1){
                e1.printStackTrace();
            }
        }
        return recipe;
    }
}
