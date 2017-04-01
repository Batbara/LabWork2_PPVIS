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

    public MenuHelp(String title, JFrame mainFrame){
        super(title);

        menuFont = new Font("Helvetica", Font.PLAIN, 13);
        UIManager.put("menu.font", menuFont);

        initMenuItems();
        addMenuItems();
    }
    private void initMenuItems(){
        hotKeysMenuItem = new JMenuItem("Горячие клавиши");
        hotKeysMenuItem.setFont(menuFont);

        aboutMenuItem = new JMenuItem("О программе...");
        aboutMenuItem.setFont(menuFont);
    }
    private void addMenuItems(){
        this.add(hotKeysMenuItem);
        this.add(aboutMenuItem);
    }
}
