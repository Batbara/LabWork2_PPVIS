package view.menu;


import controller.DataController;
import view.listeners.OpenFileListener;
import view.listeners.SaveFileListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

/**
 * Created by Batbara on 31.03.2017.
 */
public class MenuFile extends JMenu {
    JMenuItem openMenuItem;
    JMenuItem saveMenuItem;
    JMenuItem createMenuItem;
    JMenuItem closeMenuItem;

    public MenuFile(String title, JFrame owner, DataController dataController){
        super(title);

        initMenuItems();
        addMenuItems();

        OpenFileListener openFileListener = new OpenFileListener(dataController);
        openMenuItem.addActionListener(openFileListener);

        SaveFileListener saveFileListener = new SaveFileListener(dataController);
        saveMenuItem.addActionListener(saveFileListener);
    }
    private void initMenuItems(){

        openMenuItem = new JMenuItem("Открыть...");
        openMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));


        saveMenuItem = new JMenuItem("Сохранить");
        saveMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));

        createMenuItem = new JMenuItem("Новый файл");
        createMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));

        closeMenuItem = new JMenuItem("Закрыть");
        closeMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));
    }
   // private void initDialogs(JFrame owner){
//        addRecordDialog=new AddRecordDialog(owner);
//    }
    private void addMenuItems(){
        this.add(createMenuItem);
        this.add(openMenuItem);
        this.add(saveMenuItem);
        this.addSeparator();
        this.add(closeMenuItem);
    }
}
