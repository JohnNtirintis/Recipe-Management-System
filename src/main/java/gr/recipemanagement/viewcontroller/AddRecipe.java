package gr.recipemanagement.viewcontroller;

import gr.recipemanagement.dao.ingredientdao.IngredientDAOImpl;
import gr.recipemanagement.dto.recipedto.RecipeInsertDTO;
import gr.recipemanagement.factory.SQLStorageFactory;
import gr.recipemanagement.model.Ingredient;
import gr.recipemanagement.model.Recipe;
import gr.recipemanagement.service.exceptions.IngredientNotFoundDAOException;
import gr.recipemanagement.service.exceptions.RecipeNotFoundDAOException;
import gr.recipemanagement.service.recipeservice.IRecipeService;
import gr.recipemanagement.service.recipeservice.RecipeServiceImpl;
import gr.recipemanagement.validator.RecipeValidator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import static gr.recipemanagement.viewcontroller.Menu.createStyledButton;

/**
 * @author Ntirintis John
 */
public class AddRecipe extends JFrame {
    private static final long serialVersionUID = 123457;
    private JPanel contentPane;
    private JTextField recipeNameField;
    private JTextField ingredientsField;
    private JTextField instructionsField;
    private JTextField cookingTimeField;
    private JButton saveButton;
    private JButton cancelButton;
    private IngredientDAOImpl ingredientDAO;

    SQLStorageFactory factory = new SQLStorageFactory();
    IRecipeService recipeService = new RecipeServiceImpl(factory);

    public AddRecipe() {
        setTitle("Add Recipe");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setupUI();
    }

    private void setupUI() {
        // Styling as per your code
        Color backgroundColor = Menu.backgroundColor;
        Color buttonColor = Menu.buttonColor;
        Font font = Menu.buttonFont;

        setSize(400, 300);
        setLocationRelativeTo(null);


        contentPane = new JPanel();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        contentPane.setBackground(backgroundColor);
        contentPane.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        recipeNameField = new JTextField(15);
        ingredientsField = new JTextField(15);
        instructionsField = new JTextField(15);
        cookingTimeField = new JTextField(15);

        saveButton = createStyledButton("Save", buttonColor, font);
        cancelButton = createStyledButton("Cancel", buttonColor, font);

        // Set X alignment to center for each button
        saveButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        cancelButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add some rigid areas to create spacing between components
        contentPane.add(new JLabel("Recipe Name:"));
        contentPane.add(recipeNameField);
        contentPane.add(Box.createRigidArea(new Dimension(0, 10)));
        contentPane.add(new JLabel("Ingredients:"));
        contentPane.add(ingredientsField);
        contentPane.add(Box.createRigidArea(new Dimension(0, 10)));
        contentPane.add(new JLabel("Instructions: (Separate with ,)"));
        contentPane.add(instructionsField);
        contentPane.add(Box.createRigidArea(new Dimension(0, 10)));
        contentPane.add(new JLabel("Cooking Time in Minutes:"));
        contentPane.add(cookingTimeField);
        contentPane.add(Box.createRigidArea(new Dimension(0, 20)));
        contentPane.add(saveButton);
        contentPane.add(Box.createRigidArea(new Dimension(0, 5)));
        contentPane.add(cancelButton);

        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String recipeName;
                String ingredients;
                String instructions;
                double cookingTime;
                Map<String, String> recipeErrors;
                RecipeInsertDTO dto;

                try {
                    recipeName = recipeNameField.getText().trim();
                    ingredients = ingredientsField.getText();
                    instructions = instructionsField.getText().trim();
                    cookingTime = Double.parseDouble(cookingTimeField.getText());

                    dto = new RecipeInsertDTO();
                    dto.setRecipeName(recipeName);
                    dto.setInstructions(instructions);
                    dto.setCookingTime(cookingTime);

                    // Set ingredients
                    String[] allIngredients = ingredients.replaceAll("\\s", "").split(",");

                    for(int i = 0; i < allIngredients.length; i++){
                        dto.setIngredients(ingredientDAO.getByName(allIngredients[i]));
                    }

                    // Validate Date
                    recipeErrors = RecipeValidator.validate(dto);

                    String recipeNameMessage = (recipeErrors.get("recipename") != null) ? "recipename : " + recipeErrors.get("recipename") : "";
                    String instructionsMessage = (recipeErrors.get("recipemame") != null) ? "recipename : " + recipeErrors.get("recipename") : "";

                    recipeErrors = RecipeValidator.validate(dto);
                    if(!recipeErrors.isEmpty()){
                        JOptionPane.showMessageDialog(null, recipeNameMessage + " " + instructionsMessage, "Validation Error!", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    Recipe recipe = recipeService.insertRecipe(dto);

                    JOptionPane.showMessageDialog(null, "Recipe" + recipe.getRecipeName()
                            + " was inserted", "Successful Insertion!", JOptionPane.PLAIN_MESSAGE);
                } catch (RecipeNotFoundDAOException e1){
                    JOptionPane.showMessageDialog(null, e1.getMessage(), "Error! Recipe not Found.", JOptionPane.ERROR_MESSAGE);
                    e1.printStackTrace();
                } catch (IngredientNotFoundDAOException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Error! Ingredient not Found.", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        add(contentPane);
//        pack();
    }
}
