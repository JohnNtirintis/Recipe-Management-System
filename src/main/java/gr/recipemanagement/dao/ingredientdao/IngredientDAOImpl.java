package gr.recipemanagement.dao.ingredientdao;

import gr.recipemanagement.model.Ingredient;
import gr.recipemanagement.service.exceptions.IngredientNotFoundDAOException;
import gr.recipemanagement.service.util.DBUtil;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Ntirintis John
 */
public class IngredientDAOImpl implements IIngredientDAO {
    @Override
    public Ingredient insert(Ingredient ingredient) throws IngredientNotFoundDAOException {
        String sql = "INSERT INTO INGREDIENTS (INGREDIENTNAME, QUANTITY, QUANTITYTYPE) VALUES (?,?,?)";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)){

            String ingredientName = ingredient.getIngredientName();
            String quantityType = ingredient.getQuantityType();
            Double quantity = ingredient.getQuantity();

            ps.setString(1, ingredientName);
            ps.setDouble(2, quantity);
            ps.setString(3, quantityType);

            int n = ps.executeUpdate();

            if(n > 1){
                JOptionPane.showMessageDialog(null, n + "rows affected.", "Success!", JOptionPane.PLAIN_MESSAGE);
                return ingredient;
            } if(n == 1) {
                JOptionPane.showMessageDialog(null, "Successful Insertion!", "Success!", JOptionPane.PLAIN_MESSAGE);
                return ingredient;
            } else {
                return null;
            }

        } catch (SQLException e){
            e.printStackTrace();
            throw new IngredientNotFoundDAOException("Error in inserting ingredient: " + ingredient);
        }
    }

    @Override
    public Ingredient update(Ingredient ingredient) throws IngredientNotFoundDAOException {
        String sql = "UPDATE INGREDIENTS SET INGREDIENTNAME = ?, QUANTITY = ?, QUANTITYTPE = ?, INGREDIENTID = ?";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            int id = ingredient.getId();
            String ingredientName = ingredient.getIngredientName();
            String quantityType = ingredient.getQuantityType();
            Double quantity = ingredient.getQuantity();

            ps.setString(1, ingredientName);
            ps.setDouble(2, quantity);
            ps.setString(3, quantityType);
            ps.setInt(4, id);

            int n = ps.executeUpdate();

            if(n > 1){
                JOptionPane.showMessageDialog(null, n + "rows affected.", "Success!", JOptionPane.PLAIN_MESSAGE);
                return ingredient;
            } if(n == 1) {
                JOptionPane.showMessageDialog(null, "Successful update!", "Success!", JOptionPane.PLAIN_MESSAGE);
                return ingredient;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IngredientNotFoundDAOException("Error in ingredient update " + ingredient);
        }
    }

    @Override
    public void delete(int id) throws IngredientNotFoundDAOException {

        String sql = "DELETE FROM INGREDIENTS WHERE ID = ?";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, id);

            int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete ingredient with id: " + id,"Warning!", JOptionPane.YES_NO_OPTION);
            if(response == JOptionPane.YES_NO_OPTION){
                int n = ps.executeUpdate();
                if(n >= 1){
                    JOptionPane.showMessageDialog(null, n + "row(s) affected", "Successful Deletion!", JOptionPane.PLAIN_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "No row(s) affected", "Failed Deletion!", JOptionPane.PLAIN_MESSAGE);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new IngredientNotFoundDAOException("Error in deleting ingredient with id: " + id);
        }
    }

    @Override
    public Ingredient getById(int id) throws IngredientNotFoundDAOException {
        String sql = "SELECT * FROM INGREDIENTS WHERE ID = ?";
        Ingredient ingredient = null;
        ResultSet rs = null;

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)){

            ps.setInt(1, id);
            rs = ps.executeQuery();

            if(rs.next()){
                ingredient = new Ingredient(rs.getInt("INGREDIENTID"), rs.getString("INGREDIENTNAME"), rs.getDouble("QUANTITY"), rs.getString("QUANTITYTYPE"));
            }
        } catch (SQLException e){
            e.printStackTrace();
            throw new IngredientNotFoundDAOException("Error! Ingredient with id " + id + " wasn't found!");
        } finally {
            try {
                if (rs != null) rs.close();
            } catch (SQLException e1){
                e1.printStackTrace();
            }
        }
        return ingredient;
    }

    @Override
    public Ingredient getByName(String ingredientName) throws IngredientNotFoundDAOException {
        String sql = "SELECT * FROM ingredients WHERE ingredientname = ?";

        Ingredient ingredient = null;
        ResultSet rs = null;

        try(Connection connection = DBUtil.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)){

            ps.setString(1, ingredientName);
            rs = ps.executeQuery();

            if(rs.next()){
                ingredient = new Ingredient(rs.getInt("INGREDIENTID"), rs.getString("INGREDIENTNAME"), rs.getDouble("QUANTITY"), rs.getString("QUANTITYTYPE"));
            }

        } catch (SQLException e){
            e.printStackTrace();
            throw new IngredientNotFoundDAOException("Error! Ingredient with name " + ingredientName + " wasn't found!");
        } finally {
            try {
                if(rs != null){
                    rs.close();
                }
            } catch (SQLException e1){
                e1.printStackTrace();
            }
        }

        return ingredient;
    }
}
