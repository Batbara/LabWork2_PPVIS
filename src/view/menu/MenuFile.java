package view.menu;


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

    //AddRecordDialog addRecordDialog;
    Font menuFont;

    public MenuFile(String title, JFrame owner){
        super(title);

        menuFont = new Font("Helvetica", Font.PLAIN, 13);
        UIManager.put("menu.font", menuFont);

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
