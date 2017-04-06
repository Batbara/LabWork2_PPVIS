package view;
import controller.TableController;
import view.menu.*;

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
    
    MainTableModel mainTableModel;
    TableView mainTableView;
    TableController tableController;

    AddRecordDialog addRecordDialog;
    public View() {
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        initFrame();
        initMenu();
        initToolBar();

        mainTableModel = new MainTableModel();

        initMainTableView();

        tableController = new TableController(mainTableModel, mainTableView);

        addToFrame();

        //initDialogs();

    }

    private void initFrame() {
        mainFrame = new JFrame("Лабораторная работа №2");
        mainFrame.setLayout(new BorderLayout());
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(new Dimension(900, 500));
        mainFrame.setMaximumSize(new Dimension(850, 700));

       // mainFrame.getContentPane().setBackground(Color.BLUE);

        mainFrame.setResizable(false);
        mainFrame.setVisible(true);
    }

    public void initDialogs(){
        addRecordDialog = new AddRecordDialog(mainFrame, tableController);
    }
    private void addToFrame() {
        mainFrame.setJMenuBar(mainMenu.menuBar);
       mainFrame.getContentPane().add(mainToolBar.toolBarPanel, BorderLayout.NORTH);

       mainFrame.add(mainTableView.holdingTable, BorderLayout.CENTER);
    }

    private void initMenu() {
        mainMenu = new MainMenu(mainFrame, tableController);
    }

    private void initToolBar() {
        mainToolBar = new Toolbar(mainFrame, tableController);
    }

    private void initMainTableView(){
        mainTableView = new TableView(mainTableModel);

    }

}
