package view;
import controller.DataController;
import model.StudentDataBase;
import view.menu.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

import view.dialogs.*;

/**
 * Created by Batbara on 21.03.2017.
 */
public class View {
    
    public JFrame mainFrame;
    MainMenu mainMenu;
    Toolbar mainToolBar;
    
   // MainTableModel mainTableModel;
    StudentDataBase studentDataBase;
    TableView mainTableView;
    DataController dataController;

    AddRecordDialog addRecordDialog;
    public View() {
        setUIFont (new javax.swing.plaf.FontUIResource("Helvetica",Font.PLAIN,12));

        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        initFrame();
        initMainTableView();
        studentDataBase = new StudentDataBase();
        dataController = new DataController(studentDataBase, mainTableView);
        initMenu();
        initToolBar();
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
        addRecordDialog = new AddRecordDialog(mainFrame, dataController);
    }
    private void addToFrame() {
        mainFrame.setJMenuBar(mainMenu.menuBar);
       mainFrame.getContentPane().add(mainToolBar.toolBarPanel, BorderLayout.NORTH);

       mainFrame.add(mainTableView.holdingTable, BorderLayout.CENTER);
    }

    private void initMenu() {
        mainMenu = new MainMenu(mainFrame, dataController);
    }

    private void initToolBar() {
        mainToolBar = new Toolbar(mainFrame, dataController);
    }

    private void initMainTableView(){
        mainTableView = new TableView();

    }
    public DataController getDataController(){
        return dataController;
    }
    public static void setUIFont (javax.swing.plaf.FontUIResource f){
        java.util.Enumeration keys = UIManager.getDefaults().keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = UIManager.get (key);
            if (value != null && value instanceof javax.swing.plaf.FontUIResource)
                UIManager.put (key, f);
        }
    }
}
