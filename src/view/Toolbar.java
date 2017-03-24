package view;
import javax.swing.*;
import java.awt.*;

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
        newFile=initButton("newfile", newFile, "Создать");
        openFile=initButton("open",openFile,"Открыть");
        saveFile = initButton("save", saveFile, "Сохранить");

        addRecord = initButton("plus", addRecord, "Добавить");
        deleteRecord = initButton("minus", deleteRecord, "Удалить");
        findRecord = initButton("search", findRecord, "Найти");

        addToPanel();
    }
    private JButton initButton (String fileName, JButton button, String tipText){

        String imgLocation = "/img/"+fileName+".png";
        ImageIcon image = new ImageIcon(this.getClass().getResource(imgLocation));

        button = new JButton(image);
        button.setBorder(null);
        button.setMargin(new Insets(0, 0, 0, 0));

        button.setToolTipText(tipText);
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
    }
    private void addToPanel(){
        toolBarPanel.add(newFile);
        toolBarPanel.add(openFile);
        toolBarPanel.add(saveFile);
        toolBarPanel.addSeparator();
        toolBarPanel.add(addRecord);
        toolBarPanel.add(deleteRecord);
        toolBarPanel.add(findRecord);
    }
}
