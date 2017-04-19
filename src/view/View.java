package view;
import controller.DataController;
import model.StudentDataBase;
import view.menu.*;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Batbara on 21.03.2017.
 */
public class View {

    private JFrame mainFrame;
    private final DataController dataController;

    public View() {
        setUIFont(new javax.swing.plaf.FontUIResource("Helvetica", Font.PLAIN, 12));

        // final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        initFrame();

        StudentDataBase studentDataBase = new StudentDataBase();

        Paging mainPagedView = new Paging(10);
        dataController = new DataController(studentDataBase, mainPagedView);

        addControlItemsToFrame();


        JPanel buttonsPanel = mainPagedView.makeControlButtonsPanel();
        mainFrame.add(buttonsPanel, BorderLayout.PAGE_END);

    }

    private void initFrame() {
        mainFrame = new JFrame("Лабораторная работа №2");
        mainFrame.setLayout(new BorderLayout());
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setSize(new Dimension(1050, 476));
        mainFrame.setMaximumSize(new Dimension(850, 700));

        // mainFrame.getContentPane().setBackground(Color.BLUE);

        mainFrame.setResizable(false);
        mainFrame.setVisible(true);
    }

    private void addControlItemsToFrame() {
        mainFrame.setJMenuBar(createMenu().getMenuBar());
        mainFrame.getContentPane().add(setJToolBar(), BorderLayout.NORTH);

        TableView mainTableView = dataController.getPagedView();
        mainFrame.add(mainTableView.getHoldingTable(), BorderLayout.CENTER);
    }

    private MainMenu createMenu() {
        return new MainMenu(mainFrame, dataController);
    }

    private JToolBar setJToolBar() {

        JToolBar toolBarPanel = new JToolBar();
        toolBarPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        toolBarPanel.setFloatable(false);
        toolBarPanel.setRollover(true);
        toolBarPanel.setBackground(null);
        toolBarPanel.setOpaque(false);

        FileButtons fileButtons = new FileButtons(dataController);
        toolBarPanel.add(fileButtons.getNewFile());
        toolBarPanel.add(fileButtons.getOpenFile());
        toolBarPanel.add(fileButtons.getSaveFile());

        toolBarPanel.addSeparator();
        toolBarPanel.addSeparator();

        EditButtons editButtons = new EditButtons(mainFrame, dataController);
        toolBarPanel.add(editButtons.getAddRecord());
        toolBarPanel.add(editButtons.getDeleteRecord());
        toolBarPanel.add(editButtons.getSearchRecord());

        toolBarPanel.setPreferredSize(new Dimension(mainFrame.getWidth(), 50));
        toolBarPanel.setSize(new Dimension(mainFrame.getWidth(), 50));
        toolBarPanel.setMaximumSize(new Dimension(mainFrame.getWidth(), 50));
        return toolBarPanel;
    }


    private static void setUIFont (javax.swing.plaf.FontUIResource f){
        java.util.Enumeration keys = UIManager.getDefaults().keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = UIManager.get (key);
            if (value != null && value instanceof javax.swing.plaf.FontUIResource)
                UIManager.put (key, f);
        }
    }
}
