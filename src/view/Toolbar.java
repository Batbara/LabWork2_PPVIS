package view;
import controller.DataController;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Batbara on 24.03.2017.
 */
class Toolbar{

     private JToolBar toolBarPanel;

    private FileButtons fileButtons;
    private EditButtons editButtons;


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
    private void initButtons(JFrame owner, DataController dataController){
        editButtons = new EditButtons(owner, dataController);
        fileButtons = new FileButtons(dataController);
    }
    private void addToPanel(){
        toolBarPanel.add(fileButtons.getNewFile());
        toolBarPanel.add(fileButtons.getOpenFile());
        toolBarPanel.add(fileButtons.getSaveFile());

        toolBarPanel.addSeparator();
        toolBarPanel.addSeparator();

        toolBarPanel.add(editButtons.getAddRecord());
        toolBarPanel.add(editButtons.getDeleteRecord());
        toolBarPanel.add(editButtons.getSearchRecord());
    }

    private void setWindowedSize(int ownerFrameWidth){

        toolBarPanel.setPreferredSize(new Dimension(ownerFrameWidth, 50));
        toolBarPanel.setSize(new Dimension(ownerFrameWidth, 50));
        toolBarPanel.setMaximumSize(new Dimension(ownerFrameWidth,50));
    }

    public JToolBar getToolBarPanel() {
        return toolBarPanel;
    }
}
