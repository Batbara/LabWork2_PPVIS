package view.menu;

/**
 * Created by Batbara on 31.03.2017.
 */

import controller.DataController;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Batbara on 21.03.2017.
 */
public class MainMenu {
    private JMenuBar menuBar;
    MenuFile menuFile;
    MenuEdit menuEdit;
    MenuHelp menuHelp;

    public MainMenu(JFrame mainFrame, DataController tableController){

        createMenu(mainFrame, tableController);
        addToMenuBar();
    }

    private void createMenu(JFrame owner, DataController dataController) {
        menuBar= new JMenuBar();
        menuFile = new MenuFile("Файл",owner, dataController);
        menuEdit = new MenuEdit("Правка", owner, dataController);
        menuHelp = new MenuHelp("Помощь",owner);



    }
    private void addToMenuBar(){

        menuBar.add(menuFile);
        menuBar.add(menuEdit);
        menuBar.add(menuHelp);
    }

    public JMenuBar getMenuBar() {
        return menuBar;
    }
}
