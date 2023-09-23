package gr.recipemanagement.viewcontroller;

import gr.recipemanagement.dao.recipedao.IRecipeDAO;
import gr.recipemanagement.dao.recipedao.RecipeDAOImpl;
import gr.recipemanagement.service.exceptions.RecipeNotFoundDAOException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import static gr.recipemanagement.viewcontroller.Menu.createStyledButton;

/**
 * @author Ntirintis John
 */
public class ViewRecipe extends JFrame {
    private static final long serialVersionUID = 123459;
    private JPanel contentPane;
    private JComboBox<String> recipeDropdown;
    private JTextArea recipeDetailArea;
    private JButton closeButton;
    private IRecipeDAO recipeDAO;

    public ViewRecipe(IRecipeDAO recipeDAO) {
        this.recipeDAO = recipeDAO;
        setTitle("View Recipe");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setupUI();
    }

    private void setupUI() {
        // Styling same as before
        Color backgroundColor = Menu.backgroundColor;
        Color buttonColor = Menu.buttonColor;
        Font font = Menu.buttonFont;

        setSize(400, 300);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        contentPane.setBackground(backgroundColor);
        contentPane.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        recipeDropdown = new JComboBox<String>();

        recipeDetailArea = new JTextArea(10, 20);
        recipeDetailArea.setEditable(false);
        closeButton = createStyledButton("Close", buttonColor, font);

        contentPane.add(new JLabel("Select Recipe:"));
        contentPane.add(recipeDropdown);
        contentPane.add(new JScrollPane(recipeDetailArea));
        contentPane.add(closeButton);

        populateRecipeDropdown();

        recipeDropdown.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateRecipeDetails();
            }
        });

        closeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        add(contentPane);
//        pack();
    }

    private void populateRecipeDropdown(){
        try {
            List<String> recipeNames = recipeDAO.getRecipeNames();
            for(String name : recipeNames){
                recipeDropdown.addItem(name);
            }
        } catch (RecipeNotFoundDAOException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error! Recipe not found.", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void updateRecipeDetails(){
        try {
            String selectedRecipeName = (String) recipeDropdown.getSelectedItem();
            String recipeDetails = recipeDAO.getRecipeDetails(selectedRecipeName);
            recipeDetailArea.setText(recipeDetails);
        } catch (RecipeNotFoundDAOException e) {
            // Handle the exception
        }
    }

}
