package view.listeners;

import controller.DataController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.filechooser.FileFilter;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.List;

import model.Student;
import view.*;

// SAX
import org.xml.sax.SAXException;


/**
 * Created by Batbara on 14.04.2017.
 */
public class OpenFileListener implements ActionListener{
    private final JFileChooser fileChooser;
    private final DataController dataController;
    public OpenFileListener(DataController dataController) {
        fileChooser = new JFileChooser();
        FileFilter filter = new ExtensionFileFilter();
        fileChooser.setFileFilter(filter);
        this.dataController = dataController;
    }
    @Override
    public void actionPerformed(ActionEvent e) {

        if(fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            try {
                SAXParserFactory factory = SAXParserFactory.newInstance();
                SAXParser parser = factory.newSAXParser();
                XMLSaxParser handler = new XMLSaxParser();
                File file = new File(fileChooser.getSelectedFile().getPath());

                parser.parse(file, handler);
                List<Student> listData = handler.getResult();
                dataController.clearDataBase();
                dataController.loadDataBase(listData);
            }
            catch (SAXException | ParserConfigurationException | IOException eSAX) {
                    eSAX.printStackTrace();
                }

        }
    }
}
