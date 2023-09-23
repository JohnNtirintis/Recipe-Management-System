package gr.recipemanagement.dao.recipedao;

import gr.recipemanagement.dto.recipedto.RecipeInsertDTO;
import gr.recipemanagement.model.Ingredient;
import gr.recipemanagement.model.Recipe;
import gr.recipemanagement.service.exceptions.RecipeInsertException;
import gr.recipemanagement.service.exceptions.RecipeNotFoundDAOException;
import gr.recipemanagement.service.util.DBUtil;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ntirintis John
 */
public class RecipeDAOImpl implements IRecipeDAO {

    @Override
    public Recipe insert(Recipe recipe) throws RecipeNotFoundDAOException {
        String sql = "INSERT INTO RECIPES (RECIPENAME, INSTRUCTIONS, COOKINGTIME) VALUES (?,?,?)";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            String recipeName = recipe.getRecipeName();
            String instructions = recipe.getInstructions();
            double cookingTime = recipe.getCookingTime();

            ps.setString(1, recipeName);
            ps.setString(2, instructions);
            ps.setDouble(3, cookingTime);

            int affectedRows = ps.executeUpdate();


            if (affectedRows == 0) {
                throw new SQLException("Inserting recipe failed, no rows affected.");
            }

            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int id = generatedKeys.getInt(1);
                    recipe.setId(id);  // You'll need to add this setId method in your Recipe class
                } else {
                    throw new SQLException("Inserting recipe failed, no ID obtained.");
                }
            }

            return recipe;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RecipeNotFoundDAOException("SQL Error in Recipe Insertion " + recipe);
        }
    }

    @Override
    public Recipe insert(RecipeInsertDTO dto) throws RecipeInsertException {
        System.out.println("Entered overloaded RecipeDAOImpl insert");
        String sql = "INSERT INTO RECIPES (RECIPENAME, INSTRUCTIONS, COOKINGTIME) VALUES (?,?,?)";
        Recipe newRecipe = null;
        System.out.println("RecipeDAOImpl: Ran query");

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            System.out.println("RecipeDAOImpl: Entered 1st try ");
            ps.setString(1, dto.getRecipeName());
            ps.setString(2, dto.getInstructions());
            ps.setDouble(3, dto.getCookingTime());

            System.out.println("RecipeDAOImpl: Attempting to execute Update");
            int affectedRows = ps.executeUpdate();
            System.out.println("RecipeDAOImpl: Executed Update");

            if (affectedRows > 0) {
                System.out.println("RecipeDAOImpl: Entered if");
                try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                    System.out.println("RecipeDAOImpl: In 2nd try in if");
                    if (generatedKeys.next()) {
                        int id = generatedKeys.getInt(1);
                        newRecipe = new Recipe(id, dto.getRecipeName(), dto.getInstructions(), dto.getCookingTime());
                        System.out.println("RecipeDAOImpl: New recipe created, id has generated keys and got the int id");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RecipeInsertException("SQL Error in Recipe Insertion " + dto.getRecipeName());
        }
        System.out.println("RecipeDAOImpl: RETURNING NEW RECIPE - END OF OVERLOADED INSERT");
        return newRecipe;
    }

    @Override
    public Recipe update(Recipe recipe) throws RecipeNotFoundDAOException {
        String sql = "UPDATE RECIPES SET RECIPENAME = ?, INSTRUCTION = ?, COOKINGTIME = ? WHERE RECIPEID = ?";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

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
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RecipeNotFoundDAOException("Error in updating recipe " + recipe);
        }
    }

    @Override
    public void delete(int id) throws RecipeNotFoundDAOException {
        String sql = "DELETE FROM RECIPES WHERE ID = ?";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, id);

            int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete recipe with id: " + id, "Warning!", JOptionPane.YES_NO_OPTION);
            if (response == JOptionPane.YES_NO_OPTION) {
                int n = ps.executeUpdate();
                if (n >= 1) {
                    JOptionPane.showMessageDialog(null, n + "row(s) affected", "Successful Deletion!", JOptionPane.PLAIN_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "No row(s) affected", "Failed Deletion!", JOptionPane.PLAIN_MESSAGE);
                }
            }
        } catch (SQLException e) {
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
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                recipe = new Recipe(rs.getInt("RECIPEID"), rs.getString("RECIPENAME"), rs.getString("INSTRUCTIONS"), rs.getDouble("COOKINGTIME"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RecipeNotFoundDAOException("Error! Recipe with id " + id + " wasn't found!");
        } finally {
            try {
                if (rs != null) rs.close();
            } catch (SQLException e1) {
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
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, firstLetter + "%");
            rs = ps.executeQuery();

            while (rs.next()) {
                Recipe recipe = new Recipe(rs.getInt("RECIPEID"), rs.getString("RECIPENAME"),
                        rs.getString("INSTRUCTIONS"), rs.getDouble("COOKINGTIME"));
                recipes.add(recipe);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (recipes.isEmpty()) {
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
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, recipeId);
            rs = ps.executeQuery();

            while (rs.next()) {
                Ingredient ingredient = new Ingredient(
                        rs.getInt("ingredientid"),
                        rs.getString("ingredientname"),
                        rs.getDouble("quantity"),
                        rs.getString("quantitytype")
                );
                ingredients.add(ingredient);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
            } catch (SQLException e1) {
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
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, recipeName);
            rs = ps.executeQuery();

            if (!rs.next()) {
                throw new RecipeNotFoundDAOException("Error! Couldn't find recipe with name: " + recipeName);
            }

            recipe = new Recipe(rs.getInt("RECIPEID"), rs.getString("RECIPENAME"),
                    rs.getString("INSTRUCTIONS"), rs.getDouble("COOKINGTIME"));

            List<Ingredient> recipeIngredients = getRecipeIngredients(recipe.getId());
            recipe.setIngredients(recipeIngredients);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        return recipe;
    }

    @Override
    public List<String> getRecipeNames() throws RecipeNotFoundDAOException {
        String sql = "SELECT recipename FROM recipes";
        List<String> recipeNames = new ArrayList<>();

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                recipeNames.add(rs.getString("recipename"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RecipeNotFoundDAOException("SQL Error while fetching recipe names.");
        }
        return recipeNames;
    }

    @Override
    public String getRecipeDetails(String recipeName) throws RecipeNotFoundDAOException {
        String sql = "SELECT * FROM recipes WHERE recipename = ?";
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, recipeName);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    // Here, you can either populate a Recipe object or just create a formatted String
                    String instructions = rs.getString("instructions");
                    double cookingTime = rs.getDouble("cookingtime");
                    return "Instructions: " + instructions + "\nCooking Time: " + cookingTime;
                } else {
                    throw new RecipeNotFoundDAOException("Recipe with name " + recipeName + " not found.");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RecipeNotFoundDAOException("SQL Error while fetching recipe details.");
        }
    }
}
