package view.listeners;

import controller.DataController;
import view.ExtensionFileFilter;
import view.XMLDomParser;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Batbara on 14.04.2017.
 */
public class SaveFileListener implements ActionListener {
    private final JFileChooser fileChooser;
    private final DataController dataController;
    public SaveFileListener(DataController dataController) {
        fileChooser = new JFileChooser();
        FileFilter filter = new ExtensionFileFilter();
        fileChooser.setFileFilter(filter);
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
