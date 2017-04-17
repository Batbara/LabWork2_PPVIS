package view.menu;


import controller.DataController;
import view.listeners.NewFileListener;
import view.listeners.OpenFileListener;
import view.listeners.SaveFileListener;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

/**
 * Created by Batbara on 31.03.2017.
 */
class MenuFile extends JMenu {
    private JMenuItem openMenuItem;
    private JMenuItem saveMenuItem;
    private JMenuItem createMenuItem;
    private JMenuItem closeMenuItem;

    public MenuFile(JFrame owner, DataController dataController){
        super("Файл");

        initMenuItems();
        addMenuItems();

        OpenFileListener openFileListener = new OpenFileListener(dataController);
        openMenuItem.addActionListener(openFileListener);

        SaveFileListener saveFileListener = new SaveFileListener(dataController);
        saveMenuItem.addActionListener(saveFileListener);

        NewFileListener newFileListener = new NewFileListener(dataController);
        createMenuItem.addActionListener(newFileListener);

        closeMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object[] options = {"Да, вполне",
                    "Нет, я передумал",
                        "Отмена"};
                int reply = JOptionPane.showOptionDialog(owner,"Вы уверены, что хотите закрыть документ?",
                        "Закрыть", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
                        null, options, options[2]);
                if (reply == JOptionPane.YES_OPTION){
                    dataController.clearDataBase();
                    dataController.getPagedView().showCurrentPage();
                }
            }
        });
    }
    private void initMenuItems(){

        openMenuItem = new JMenuItem("Открыть...");
        openMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));


        saveMenuItem = new JMenuItem("Сохранить");
        saveMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));

        createMenuItem = new JMenuItem("Новый файл");
        createMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));

        closeMenuItem = new JMenuItem("Закрыть");
        closeMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_MASK));
    }

    private void addMenuItems(){
        this.add(createMenuItem);
        this.add(openMenuItem);
        this.add(saveMenuItem);
        this.addSeparator();
        this.add(closeMenuItem);
    }
}
