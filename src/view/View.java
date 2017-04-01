package view;
import view.menu.*;
import view.toolbar.*;
import javax.swing.*;
import java.awt.*;

import view.dialogs.*;
import view.toolbar.Toolbar;

/**
 * Created by Batbara on 21.03.2017.
 */
public class View {
    public JFrame mainFrame;
    MainMenu mainMenu;
    Toolbar mainToolBar;

    AddRecordDialog addRecordDialog;
    public View() {
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        initFrame();
        initMenu();
        initToolBar();
        addToFrame();


        //initDialogs();

    }

    private void initFrame() {
        mainFrame = new JFrame("Лабораторная работа №2");
        mainFrame.setLayout(new FlowLayout());
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(new Dimension(650, 500));
        mainFrame.setMaximumSize(new Dimension(550, 700));

        mainFrame.setResizable(false);
        mainFrame.setVisible(true);
    }

    public void initDialogs(){
        addRecordDialog = new AddRecordDialog(mainFrame);
    }
    private void addToFrame() {
        mainFrame.setJMenuBar(mainMenu.menuBar);
       mainFrame.add(mainToolBar.toolBarPanel, BorderLayout.WEST);
    }

    private void initMenu() {
        mainMenu = new MainMenu(mainFrame);
    }

    private void initToolBar() {
        mainToolBar = new Toolbar(mainFrame);
    }


}
