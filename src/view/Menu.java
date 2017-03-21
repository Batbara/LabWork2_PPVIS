package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

/**
 * Created by Batbara on 21.03.2017.
 */
public class Menu {
    JMenuBar menuBar;
    JMenu menuFile;
    JMenu menuEdit;
    JMenu menuHelp;

    JMenuItem openMenuItem;
    JMenuItem saveMenuItem;
    JMenuItem createMenuItem;
    JMenuItem closeMenuItem;

    JMenuItem addRecordMenuItem;
    JMenuItem delMenuItem;
    JMenuItem findMenuItem;

    JMenuItem hotKeysMenuItem;
    JMenuItem aboutMenuItem;

    public Menu(){

        createMenu();
        initMenuItems();
        addMenuItems();

        menuBar.add(menuFile);
        menuBar.add(menuEdit);
        menuBar.add(menuHelp);
    }

    private void createMenu() {
        menuBar= new JMenuBar();
        menuFile = new JMenu("Файл");
        menuEdit = new JMenu("Правка");
        menuHelp = new JMenu("Помощь");

        menuBar.add(menuFile);
        menuBar.add(menuEdit);
        menuBar.add(menuHelp);
    }
    private void initMenuItems (){
        openMenuItem = new JMenuItem("Открыть...");
        openMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));

        saveMenuItem = new JMenuItem("Сохранить");
        saveMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));

        createMenuItem = new JMenuItem("Новый файл");
        createMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));

        closeMenuItem = new JMenuItem("Закрыть");
        closeMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));

        addRecordMenuItem = new JMenuItem("Добавить запись");
        delMenuItem = new JMenuItem("Удалить записи");

        findMenuItem = new JMenuItem("Поиск...");

        hotKeysMenuItem = new JMenuItem("Горячие клавиши");
        aboutMenuItem = new JMenuItem("О программе...");
    }
    private void addMenuItems(){

        menuFile.add(createMenuItem);
        menuFile.add(openMenuItem);
        menuFile.add(saveMenuItem);
        menuFile.addSeparator();
        menuFile.add(closeMenuItem);

        menuEdit.add(addRecordMenuItem);
        menuEdit.add(findMenuItem);
        menuEdit.add(delMenuItem);

        menuHelp.add(hotKeysMenuItem);
        menuHelp.add(aboutMenuItem);
    }
}
