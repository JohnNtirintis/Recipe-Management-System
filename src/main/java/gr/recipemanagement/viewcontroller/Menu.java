package gr.recipemanagement.viewcontroller;

import gr.recipemanagement.dao.recipedao.IRecipeDAO;
import gr.recipemanagement.dao.recipedao.RecipeDAOImpl;
import gr.recipemanagement.factory.SQLStorageFactory;
import gr.recipemanagement.factory.StorageFactory;

import javax.swing.*;
import java.sql.Connection;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * @author Ntirintis John
 */
public class Menu extends JFrame {

    private static final long serialVersionUID = 123456;
    private JPanel contentPane;
    private static Connection connection;
    private JButton addIngredientButton;
    private JButton editIngredientButton;
    private JButton viewIngredientButton;
    private JButton exitButton;
    IRecipeDAO iRecipeDAO = new RecipeDAOImpl();


    // Styling
    public static final Color backgroundColor = new Color(245, 245, 245);
    public static final Color buttonColor = new Color(100, 149, 237);
    public static final Font buttonFont = new Font("Arial", Font.BOLD, 16);

    public Menu() {
        // Frame configuration

        setTitle("Cooking Recipes App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(400, 300);
        setLocationRelativeTo(null);

        Color backgroundColor = Menu.backgroundColor;
        Color buttonColor = Menu.buttonColor;
        Font font = Menu.buttonFont;

        // Create main panel with BoxLayout for vertical alignment
        JPanel mainMenuPanel = new JPanel();
        mainMenuPanel.setLayout(new BoxLayout(mainMenuPanel, BoxLayout.Y_AXIS));
        mainMenuPanel.setBackground(backgroundColor);
        mainMenuPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Set X alignment to center for each button
        addIngredientButton = createStyledButton("Add Recipe", buttonColor, buttonFont);
        addIngredientButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        editIngredientButton = createStyledButton("Edit Recipe", buttonColor, buttonFont);
        editIngredientButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        viewIngredientButton = createStyledButton("View Recipes", buttonColor, buttonFont);
        viewIngredientButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitButton = createStyledButton("Exit", buttonColor, buttonFont);
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add buttons to the main panel with some spacing
        mainMenuPanel.add(addIngredientButton);
        mainMenuPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        mainMenuPanel.add(editIngredientButton);
        mainMenuPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        mainMenuPanel.add(viewIngredientButton);
        mainMenuPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        mainMenuPanel.add(exitButton);

        addIngredientButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new AddRecipe().setVisible(true);
            }
        });

        editIngredientButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openEditRecipeFrame();
            }
        });

        viewIngredientButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ViewRecipe(iRecipeDAO).setVisible(true);
            }
        });

        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // Add the main panel to the frame
        add(mainMenuPanel);

        // Pack the frame
//        pack();
    }
    private void openEditRecipeFrame() {
        StorageFactory factory = new SQLStorageFactory();
        EditRecipeEclipse editFrame = new EditRecipeEclipse(factory);
        editFrame.setVisible(true);
    }

    public static JButton createStyledButton(String text, Color color, Font font) {
        JButton button = new JButton(text);
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFont(font);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return button;
    }
}
