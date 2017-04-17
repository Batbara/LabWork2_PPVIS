package view.menu;

import controller.DataController;

import javax.swing.*;

/**
 * Created by Batbara on 21.03.2017.
 */
public class MainMenu {
    private JMenuBar menuBar;
    private MenuFile menuFile;
    private MenuEdit menuEdit;
   // private MenuHelp menuHelp;

    public MainMenu(JFrame mainFrame, DataController tableController){

        createMenu(mainFrame, tableController);
        addToMenuBar();
    }

    private void createMenu(JFrame owner, DataController dataController) {
        menuBar= new JMenuBar();
        menuFile = new MenuFile(owner, dataController);
        menuEdit = new MenuEdit(owner, dataController);
        //menuHelp = new MenuHelp(owner);



    }
    private void addToMenuBar(){

        menuBar.add(menuFile);
        menuBar.add(menuEdit);
      //  menuBar.add(menuHelp);
    }

    public JMenuBar getMenuBar() {
        return menuBar;
    }
}
