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

    Font menuFont;

    public Menu(){

        menuFont = new Font("Helvetica", Font.PLAIN, 13);
        UIManager.put("Menu.font", menuFont);
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

        menuFile.setFont(menuFont);

        menuBar.add(menuFile);
        menuBar.add(menuEdit);
        menuBar.add(menuHelp);
    }
    private void initMenuItems (){
        openMenuItem = new JMenuItem("Открыть...");
        openMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        openMenuItem.setFont(menuFont);

        saveMenuItem = new JMenuItem("Сохранить");
        saveMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        saveMenuItem.setFont(menuFont);

        createMenuItem = new JMenuItem("Новый файл");
        createMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        createMenuItem.setFont(menuFont);

        closeMenuItem = new JMenuItem("Закрыть");
        closeMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));
        closeMenuItem.setFont(menuFont);

        addRecordMenuItem = new JMenuItem("Добавить запись");
        addRecordMenuItem.setFont(menuFont);

        delMenuItem = new JMenuItem("Удалить записи");
        delMenuItem.setFont(menuFont);

        findMenuItem = new JMenuItem("Поиск...");
        findMenuItem.setFont(menuFont);

        hotKeysMenuItem = new JMenuItem("Горячие клавиши");
        hotKeysMenuItem.setFont(menuFont);

        aboutMenuItem = new JMenuItem("О программе...");
        aboutMenuItem.setFont(menuFont);
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
