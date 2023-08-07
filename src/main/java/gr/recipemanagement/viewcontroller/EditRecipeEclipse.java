package gr.recipemanagement.viewcontroller;

import gr.recipemanagement.factory.StorageFactory;
import gr.recipemanagement.model.Recipe;
import gr.recipemanagement.service.exceptions.RecipeNotFoundDAOException;
import gr.recipemanagement.service.recipeservice.IRecipeService;
import gr.recipemanagement.service.recipeservice.RecipeServiceImpl;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

import static gr.recipemanagement.viewcontroller.Menu.createStyledButton;

/**
 * @author Ntirintis John
 */
public class EditRecipeEclipse extends JFrame {

    private int listSize = 0;
    private int listPosition = 0;
    private List<Recipe> recipes;
    private List<String> recipeNames;

    private JPanel contentPane;
    private JComboBox<String> recipesComboBox;
    private JTextField recipeNameTxt;
    private JTextField ingredientsTxt;
    private JTextField instructionsTxt;
    private JTextField cookingTimeTxt;
    private JButton searchBtn;
    private JButton updateBtn;
    private JButton cancelBtn;

    private IRecipeService recipeService;

    public EditRecipeEclipse(StorageFactory factory) {
        this.recipeService = new RecipeServiceImpl(factory);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        Color backgroundColor = Menu.backgroundColor;
        Color buttonColor = Menu.buttonColor;
        Font font = Menu.buttonFont;

        contentPane.setLayout(null);
        contentPane.setBackground(backgroundColor);

        searchBtn = createStyledButton("Search", buttonColor, font);
        searchBtn.setBounds(58, 300, 100, 23);
        contentPane.add(searchBtn);

        updateBtn = createStyledButton("Update", buttonColor, font);
        updateBtn.setBounds(188, 300, 100, 23);
        contentPane.add(updateBtn);

        cancelBtn = createStyledButton("Cancel", buttonColor, font);
        cancelBtn.setBounds(318, 300, 100, 23);
        contentPane.add(cancelBtn);

        recipeNameTxt = new JTextField();
        recipeNameTxt.setColumns(10);
        recipeNameTxt.setBounds(81, 80, 320, 20);
        contentPane.add(recipeNameTxt);

        ingredientsTxt = new JTextField();
        ingredientsTxt.setColumns(10);
        ingredientsTxt.setBounds(81, 122, 302, 20);
        contentPane.add(ingredientsTxt);

        instructionsTxt = new JTextField();
        instructionsTxt.setColumns(10);
        instructionsTxt.setBounds(81, 209, 302, 45);
        contentPane.add(instructionsTxt);

        cookingTimeTxt = new JTextField();
        cookingTimeTxt.setColumns(10);
        cookingTimeTxt.setBounds(188, 166, 88, 20);
        contentPane.add(cookingTimeTxt);

        recipesComboBox = new JComboBox<String>();
        recipesComboBox.setBounds(81, 25, 302, 22);
        contentPane.add(recipesComboBox);

        JLabel recipesLbl = new JLabel("Recipes");
        recipesLbl.setBounds(209, 5, 55, 14);
        contentPane.add(recipesLbl);

        JLabel recipeNameLbl = new JLabel("Recipe Name");
        recipeNameLbl.setBounds(197, 55, 75, 14);
        contentPane.add(recipeNameLbl);

        JLabel instructionsLbl = new JLabel("Instructions");
        instructionsLbl.setBounds(201, 191, 80, 14);
        contentPane.add(instructionsLbl);

        JLabel cookingTimeLbl = new JLabel("Cooking Time");
        cookingTimeLbl.setBounds(196, 150, 95, 14);
        contentPane.add(cookingTimeLbl);

        JLabel ingredientsLbl = new JLabel("Ingredients");
        ingredientsLbl.setBounds(201, 103, 85, 14);
        contentPane.add(ingredientsLbl);

        setContentPane(contentPane);

        cancelBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        searchBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    System.out.println("recipeService: " + recipeService);
                    System.out.println("recipeNameTxt: " + recipeNameTxt);
                    System.out.println("recipesComboBox: " + recipesComboBox);
                    recipes = recipeService.getByFirstLetter(recipeNameTxt.getText());

                    // Remove any previous items if there are any
                    recipesComboBox.removeAllItems();

                    // Get total amount of recipes found
                    listSize = recipes.size();

                    if(listSize == 0){
                        JOptionPane.showMessageDialog(null, "No recipes found!", "Whoops!", JOptionPane.ERROR_MESSAGE);
                        return;
                    } else {
                        JOptionPane.showMessageDialog(null, listSize + " recipe(s) found!", "Success!", JOptionPane.INFORMATION_MESSAGE);
                    }

                    for(Recipe recipe: recipes){
                        recipesComboBox.addItem(recipe.getRecipeName());
                    }

                    listPosition = 0;
                } catch (RecipeNotFoundDAOException e1){
                    JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        recipesComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED){
                    // Get Selected Recipe Name
                    String selectedRecipeName = (String) e.getItem();

                    // Fetch the Recipe object with that name
                    Recipe selectedRecipe = recipes.stream()
                            .filter(r -> r.getRecipeName().equals(selectedRecipeName))
                            .findFirst()
                            .orElse(null);

                    // Update the text fields
                    if(selectedRecipe != null){
                        recipeNameTxt.setText(selectedRecipe.getRecipeName());
                        String ingredients = selectedRecipe.getIngredients() != null ? selectedRecipe.getIngredients().toString() : "No ingredients";
                        ingredientsTxt.setText(ingredients);
                        instructionsTxt.setText(selectedRecipe.getInstructions());
                        cookingTimeTxt.setText(String.valueOf(selectedRecipe.getCookingTime()));
                    }
                }
            }
        });
    }
}
