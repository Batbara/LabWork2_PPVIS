package view;
import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.beans.PropertyChangeListener;

/**
 * Created by Batbara on 24.03.2017.
 */
public class Toolbar{
    JToolBar toolBarPanel;

    JButton newFile;
    JButton openFile;
    JButton saveFile;

    JButton addRecord;
    JButton deleteRecord;
    JButton findRecord;

    public Toolbar (){

        initToolBarPanel();

        newFile=initButton("newfile", "newfilehover", "newfilepressed",
                 "Создать");
        openFile=initButton("open","openhover", "openpressed","Открыть");
        saveFile = initButton("save", "savehover", "savepressed",
                "Сохранить");

       addRecord = initButton("plus", "plushover", "pluspressed","Добавить");
        deleteRecord = initButton("minus", "minushover", "minuspressed",
                "Удалить");
        findRecord = initButton("search", "searchhover", "searchpressed",
                "Найти");

        addToPanel();
    }
    private JButton initButton (String fileName, String hoverFileName, String pressedFileName,
                                 String tipText){

        String mainImgLocation = "/img/"+fileName+".png";
        String hoverImgLocation = "/img/"+hoverFileName+".png";
        String pressedImgLocation = "/img/"+pressedFileName+".png";

        ImageIcon image = new ImageIcon(this.getClass().getResource(mainImgLocation));
        ImageIcon hoverImage = new ImageIcon(this.getClass().getResource(hoverImgLocation));
        ImageIcon pressedImage = new ImageIcon(this.getClass().getResource(pressedImgLocation));


        JButton button = new JButton();
        button.setIcon(image);
        button.setBorder(null);
       // button.setMargin(new Insets(0, 0, 0, 0));
        button.setBackground(null);
        button.setContentAreaFilled(false);
        button.setToolTipText(tipText);

        button.getModel().addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                ButtonModel model = (ButtonModel) e.getSource();
                if (model.isRollover()) {
                    button.setIcon(hoverImage);
                } else {
                   button.setIcon(image);
                }
                if (model.isPressed())
                    button.setIcon(pressedImage);
            }
        });

        return button;

    }
    private void initToolBarPanel(){
        toolBarPanel = new JToolBar();
        toolBarPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        toolBarPanel.setPreferredSize(new Dimension(550, 50));
        toolBarPanel.setSize(new Dimension(550, 50));
        toolBarPanel.setMaximumSize(new Dimension(550,50));
        toolBarPanel.setFloatable(false);
        toolBarPanel.setRollover(true);
        toolBarPanel.setBackground(null);
        toolBarPanel.setOpaque(false);
    }
    private void addToPanel(){
        toolBarPanel.add(newFile);
        toolBarPanel.add(openFile);
        toolBarPanel.add(saveFile);
        JSeparator vertSeparator = new JSeparator(SwingConstants.VERTICAL);
       // toolBarPanel.addSeparator();
        toolBarPanel.add(vertSeparator);
        toolBarPanel.add(addRecord);
        toolBarPanel.add(deleteRecord);
        toolBarPanel.add(findRecord);
    }
}
