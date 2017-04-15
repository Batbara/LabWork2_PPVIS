package view.listeners;

import controller.DataController;

import javax.swing.*;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.parsers.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.List;

import model.Student;
import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;
import view.XMLSaxParser;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.parsers.SAXParser;

// SAX
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


/**
 * Created by Batbara on 14.04.2017.
 */
public class OpenFileListener implements ActionListener{
    private JFileChooser fileChooser;
    private DataController dataController;
    public OpenFileListener(DataController dataController) {
        fileChooser = new JFileChooser();
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
            catch (SAXException eSAX) {
                    eSAX.printStackTrace();
                } catch (IOException eIO) {
                    eIO.printStackTrace();
                }
                catch (ParserConfigurationException ePCE){
                ePCE.printStackTrace();
                }

        }
    }
}
