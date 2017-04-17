package view.menu;

import controller.DataController;
import view.listeners.AddRecordListener;
import view.listeners.DeleteRecordListener;
import view.listeners.SearchRecordsListener;


import javax.swing.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

/**
 * Created by student on 31.03.2017.
 */
class MenuEdit extends JMenu {

    private JMenuItem addRecordMenuItem;
    private JMenuItem delMenuItem;
    private JMenuItem searchMenuItem;

    public MenuEdit(JFrame ownerFrame, DataController dataController){
        super("Правка");

        initMenuItems();
        addMenuItems();

        AddRecordListener addRecordListener = new AddRecordListener(ownerFrame, dataController);
        addRecordMenuItem.addActionListener(addRecordListener);

        DeleteRecordListener deleteRecordListener = new DeleteRecordListener(ownerFrame, dataController);
        delMenuItem.addActionListener(deleteRecordListener);

        SearchRecordsListener searchRecordsListener = new SearchRecordsListener(ownerFrame, dataController);
        searchMenuItem.addActionListener(searchRecordsListener);

    }
    private void initMenuItems(){

        addRecordMenuItem = new JMenuItem("Добавить запись");
        addRecordMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK));

        delMenuItem = new JMenuItem("Удалить записи");
        delMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.CTRL_MASK));

        searchMenuItem = new JMenuItem("Поиск...");
        searchMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.CTRL_MASK));

    }

    private void addMenuItems(){
        this.add(addRecordMenuItem);
        this.add(searchMenuItem);
        this.add(delMenuItem);
    }

}
