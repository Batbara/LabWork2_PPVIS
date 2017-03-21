package view;

import javax.swing.*;
import java.awt.*;
/**
 * Created by Batbara on 21.03.2017.
 */
public class View {
            JFrame mainFrame;
            Menu maiMenu;
//        JMenuBar menuBar;
//        JMenu menuFile;
//        JMenu menuEdit;
//        JMenu menuHelp;

        public View(){

            initFrame();
            initMenu();
            addMenuToFrame();



        }
        private void initFrame(){
            mainFrame = new JFrame("Лабораторная работа №2");
            mainFrame.setLayout(new FlowLayout());
            mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            mainFrame.setSize(new Dimension(550, 700));
            mainFrame.setMaximumSize(new Dimension(550, 700));

            mainFrame.setVisible(true);
        }
        /*private void createMenu() {
            menuBar= new JMenuBar();
            menuFile = new JMenu("Файл");
            menuEdit = new JMenu("Правка");
            menuHelp = new JMenu("Помощь");

            menuBar.add(menuFile);
            menuBar.add(menuEdit);
            menuBar.add(menuHelp);
        }*/
        private void addMenuToFrame(){
            mainFrame.setJMenuBar(maiMenu.menuBar);
        }
        private void initMenu(){
            maiMenu = new Menu();
        }
    public static void main(String[] args) {

        javax.swing.SwingUtilities.invokeLater(new Runnable(){
                                                   public void run(){
                                                       new View();
                                                   }
                                               }
        );
    }
}
