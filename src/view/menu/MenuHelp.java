package view.menu;

import javax.swing.*;
import java.awt.*;

/**
 * Created by student on 31.03.2017.
 */
public class MenuHelp extends JMenu{

    JMenuItem hotKeysMenuItem;
    JMenuItem aboutMenuItem;

    Font menuFont;

    public MenuHelp(String title){
        super(title);
        initMenuItems();
        addMenuItems();
    }
    private void initMenuItems(){
        hotKeysMenuItem = new JMenuItem("������� �������");
        hotKeysMenuItem.setFont(menuFont);

        aboutMenuItem = new JMenuItem("� ���������...");
        aboutMenuItem.setFont(menuFont);
    }
    private void addMenuItems(){
        this.add(hotKeysMenuItem);
        this.add(aboutMenuItem);
    }
}
