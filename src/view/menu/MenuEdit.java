package view.menu;

import view.dialogs.AddRecordDialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by student on 31.03.2017.
 */
public class MenuEdit extends JMenu {

    JMenuItem addRecordMenuItem;
    JMenuItem delMenuItem;
    JMenuItem findMenuItem;

    //AddRecordDialog addRecordDialog;
    Font menuFont;

    public MenuEdit(String title, JFrame owner){
        super(title);
        initMenuItems();
        addMenuItems();
        //initDialogs(owner);
//        addRecordMenuItem.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                addRecordDialog.setVisible(true);
//            }
//        });
    }
    private void initMenuItems(){

        addRecordMenuItem = new JMenuItem("Добавить запись");
        addRecordMenuItem.setFont(menuFont);

        delMenuItem = new JMenuItem("Удалить записи");
        delMenuItem.setFont(menuFont);

        findMenuItem = new JMenuItem("Поиск...");
        findMenuItem.setFont(menuFont);

    }
//    private void initDialogs(JFrame owner){
//        addRecordDialog=new AddRecordDialog(owner);
//    }
    private void addMenuItems(){
        this.add(addRecordMenuItem);
        this.add(findMenuItem);
        this.add(delMenuItem);
    }
}
