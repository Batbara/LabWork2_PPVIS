package view.menu;

/**
 * Created by Batbara on 31.03.2017.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

/**
 * Created by Batbara on 21.03.2017.
 */
public class MainMenu {
    JMenuBar menuBar;
    MenuFile menuFile;
    MenuEdit menuEdit;
    MenuHelp menuHelp;

    Font menuFont;

    public MainMenu(JFrame mainFrame){

        menuFont = new Font("Helvetica", Font.PLAIN, 13);
        UIManager.put("menu.font", menuFont);
        createMenu(mainFrame);
        addToMenuBar();
    }

    private void createMenu(JFrame owner) {
        menuBar= new JMenuBar();
        menuFile = new MenuFile("Файл",owner);
        menuEdit = new MenuEdit("Правка", owner);
        menuHelp = new MenuHelp("Помощь",owner);

        menuFile.setFont(menuFont);

    }
    private void addToMenuBar(){

        menuBar.add(menuFile);
        menuBar.add(menuEdit);
        menuBar.add(menuHelp);
    }

}
