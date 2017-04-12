package view.menu;

import controller.DataController;
import view.dialogs.*;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

/**
 * Created by student on 31.03.2017.
 */
public class MenuEdit extends JMenu {

    JMenuItem addRecordMenuItem;
    JMenuItem delMenuItem;
    JMenuItem findMenuItem;

    AddRecordDialog addRecordDialog;
    DelRecordDialog delRecordDialog;

    Font menuFont;

    public MenuEdit(String title, JFrame owner, DataController tableController){
        super(title);
        menuFont = new Font("Helvetica", Font.PLAIN, 13);
        UIManager.put("menu.font", menuFont);

        initMenuItems();
        addMenuItems();
        initDialogs(owner, tableController);

        addRecordMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addRecordDialog.centerOnScreen();
                addRecordDialog.setVisible(true);
            }
        });

        /*delMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                delRecordDialog.centerOnScreen();
                delRecordDialog.setVisible(true);
                delRecordDialog.setVisibleTextFieldItems(false);
                delRecordDialog.noneSelected();
                //System.out.println("i am here");

            }
        });*/

    }
    private void initMenuItems(){

        addRecordMenuItem = new JMenuItem("Добавить запись");
        addRecordMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
        addRecordMenuItem.setFont(menuFont);

        delMenuItem = new JMenuItem("Удалить записи");
        addRecordMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
        delMenuItem.setFont(menuFont);

        findMenuItem = new JMenuItem("Поиск...");
        findMenuItem.setFont(menuFont);

    }
    private void initDialogs(JFrame owner, DataController dataController){
        addRecordDialog=new AddRecordDialog(owner, dataController);
        delRecordDialog = new DelRecordDialog(owner, dataController);
    }
    private void addMenuItems(){
        this.add(addRecordMenuItem);
        this.add(findMenuItem);
        this.add(delMenuItem);
    }

}
