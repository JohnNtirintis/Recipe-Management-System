package gr.recipemanagement;

import gr.recipemanagement.viewcontroller.Menu;

import java.awt.*;

/**
 * @author Ntirintis John
 */

// TODO: Add ingredients input filed in "add recipe
// TODO:  Complete View Recipes

public class Main {

    private static Menu menu;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    menu = new Menu();
                    menu.setVisible(true);

                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }
}
