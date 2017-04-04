package view.menu;

/**
 * Created by Batbara on 31.03.2017.
 */

import controller.TableController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

/**
 * Created by Batbara on 21.03.2017.
 */
public class MainMenu {
    public JMenuBar menuBar;
    MenuFile menuFile;
    MenuEdit menuEdit;
    MenuHelp menuHelp;

    Font menuFont;

    public MainMenu(JFrame mainFrame, TableController tableController){

        menuFont = new Font("Helvetica", Font.PLAIN, 13);
        UIManager.put("menu.font", menuFont);
        createMenu(mainFrame, tableController);
        addToMenuBar();
    }

    private void createMenu(JFrame owner, TableController tableController) {
        menuBar= new JMenuBar();
        menuFile = new MenuFile("Файл",owner);
        menuEdit = new MenuEdit("Правка", owner, tableController);
        menuHelp = new MenuHelp("Помощь",owner);

        menuFile.setFont(menuFont);
        menuEdit.setFont(menuFont);
        menuHelp.setFont(menuFont);

    }
    private void addToMenuBar(){

        menuBar.add(menuFile);
        menuBar.add(menuEdit);
        menuBar.add(menuHelp);
    }

}
