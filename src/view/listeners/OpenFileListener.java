package view.listeners;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Batbara on 14.04.2017.
 */
public class OpenFileListener implements ActionListener {
    private JFileChooser fileChooser;
    public OpenFileListener() {
        fileChooser = new JFileChooser();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        fileChooser.showOpenDialog(null);
    }
}
