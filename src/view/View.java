package view;
import view.menu.*;
import javax.swing.*;
import java.awt.*;

/**
 * Created by Batbara on 21.03.2017.
 */
public class View {
    public JFrame mainFrame;
    MainMenu mainMenu;
    Toolbar mainToolBar;
//        JMenuBar menuBar;
//        JMenu menuFile;
//        JMenu menuEdit;
//        JMenu menuHelp;

    public View() {

        initFrame();
        initMenu();
//        initToolBar();
        addToFrame();


    }

    private void initFrame() {
        mainFrame = new JFrame("Лабораторная работа №2");
        mainFrame.setLayout(new FlowLayout());
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(new Dimension(550, 700));
        mainFrame.setMaximumSize(new Dimension(550, 700));

        mainFrame.setVisible(true);
    }

    /*private void createMenu() {
        menuBar= new JMenuBar();
        menuFile = new JMenu("Файл");
        menuEdit = new JMenu("Правка");
        menuHelp = new JMenu("Помощь");

        menuBar.add(menuFile);
        menuBar.add(menuEdit);
        menuBar.add(menuHelp);
    }*/
    private void addToFrame() {
        mainFrame.setJMenuBar(mainMenu.menuBar);
//        mainFrame.add(mainToolBar.toolBarPanel, BorderLayout.WEST);
    }

    private void initMenu() {
        mainMenu = new MainMenu();
    }

    private void initToolBar() {
        mainToolBar = new Toolbar();
    }


}
