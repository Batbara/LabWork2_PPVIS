package view.menu;

import javax.swing.*;
import java.awt.*;

/**
 * Created by student on 31.03.2017.
 */
public class MenuEdit extends JMenu {

    JMenuItem addRecordMenuItem;
    JMenuItem delMenuItem;
    JMenuItem findMenuItem;

    Font menuFont;

    public MenuEdit(String title){
        super(title);
        initMenuItems();
        addMenuItems();
    }
    private void initMenuItems(){
        addRecordMenuItem = new JMenuItem("�������� ������");
        addRecordMenuItem.setFont(menuFont);

        delMenuItem = new JMenuItem("������� ������");
        delMenuItem.setFont(menuFont);

        findMenuItem = new JMenuItem("�����...");
        findMenuItem.setFont(menuFont);
    }
    private void addMenuItems(){
        this.add(addRecordMenuItem);
        this.add(findMenuItem);
        this.add(delMenuItem);
    }
}
