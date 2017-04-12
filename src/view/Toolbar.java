package view;
import controller.DataController;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Batbara on 24.03.2017.
 */
public class Toolbar{

     public JToolBar toolBarPanel;

    FileButtons fileButtons;
    EditButtons editButtons;


    public Toolbar (JFrame ownerFrame, DataController tableController){

        initToolBarPanel(ownerFrame);
        initButtons(ownerFrame, tableController);
        addToPanel();
    }

    private void initToolBarPanel(JFrame ownerFrame){

        int ownerFrameWidth = ownerFrame.getWidth();

        toolBarPanel = new JToolBar();
        toolBarPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        setWindowedSize(ownerFrameWidth);

        toolBarPanel.setFloatable(false);
        toolBarPanel.setRollover(true);
        toolBarPanel.setBackground(null);
        toolBarPanel.setOpaque(false);

    }
    private void initButtons(JFrame owner, DataController tableController){
        editButtons = new EditButtons(owner, tableController);
        fileButtons = new FileButtons();
    }
    private void addToPanel(){
        toolBarPanel.add(fileButtons.newFile);
        toolBarPanel.add(fileButtons.openFile);
        toolBarPanel.add(fileButtons.saveFile);

        toolBarPanel.addSeparator();
        toolBarPanel.addSeparator();

        toolBarPanel.add(editButtons.addRecord);
        toolBarPanel.add(editButtons.deleteRecord);
        toolBarPanel.add(editButtons.findRecord);
    }

    private void setWindowedSize(int ownerFrameWidth){

        toolBarPanel.setPreferredSize(new Dimension(ownerFrameWidth, 50));
        toolBarPanel.setSize(new Dimension(ownerFrameWidth, 50));
        toolBarPanel.setMaximumSize(new Dimension(ownerFrameWidth,50));
    }

}