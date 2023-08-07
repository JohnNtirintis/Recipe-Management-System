package gr.recipemanagement.viewcontroller;

import gr.recipemanagement.factory.SQLStorageFactory;
import gr.recipemanagement.service.recipeservice.IRecipeService;
import gr.recipemanagement.service.recipeservice.RecipeServiceImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static gr.recipemanagement.viewcontroller.Menu.createStyledButton;

/**
 * @author Ntirintis John
 */
public class EditRecipe extends JFrame {
    private static final long serialVersionUID = 123458;
    private JPanel contentPane;
    private JComboBox<String> recipeDropdown;
    private JTextField recipeNameField;
    private JTextField instructionsField;
    private JTextField cookingTimeField;
    private JButton updateButton;
    private JButton cancelButton;

    SQLStorageFactory factory =  new SQLStorageFactory();
    IRecipeService recipeService = new RecipeServiceImpl(factory);


    public EditRecipe() {
        setTitle("Edit Recipe");
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

        // Assuming a list of recipe names for editing
        recipeDropdown = new JComboBox<String>();
        recipeDropdown.addItem("Sample Recipe 1");
        recipeDropdown.addItem("Sample Recipe 2");

        recipeNameField = new JTextField(15);
        instructionsField = new JTextField(15);
        cookingTimeField = new JTextField(15);

        updateButton = createStyledButton("Update", buttonColor, font);
        cancelButton = createStyledButton("Cancel", buttonColor, font);

        contentPane.add(new JLabel("Select Recipe:"));
        contentPane.add(recipeDropdown);
        contentPane.add(new JLabel("Recipe Name:"));
        contentPane.add(recipeNameField);
        contentPane.add(new JLabel("Instructions:"));
        contentPane.add(instructionsField);
        contentPane.add(new JLabel("Cooking Time:"));
        contentPane.add(cookingTimeField);
        contentPane.add(updateButton);
        contentPane.add(cancelButton);

        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        add(contentPane);
//        pack();
    }
}
