package view.listeners;

import controller.DataController;
import view.XMLDomParser;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Batbara on 14.04.2017.
 */
public class SaveFileListener implements ActionListener {
    private JFileChooser fileChooser;
    DataController dataController;
    public SaveFileListener(DataController dataController) {
        fileChooser = new JFileChooser();
        this.dataController = dataController;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {

            XMLDomParser writeParser = new XMLDomParser(dataController, fileChooser.getSelectedFile().getPath());
            writeParser.parseIntoFile();
        }
    }
}
