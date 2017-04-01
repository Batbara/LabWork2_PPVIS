package view.toolbar;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Created by Batbara on 02.04.2017.
 */
public class FileButtons {
    public JButton newFile;
    public JButton openFile;
    public JButton saveFile;

    public FileButtons(){
        newFile=initButton("newfile", "newfilehover", "newfilepressed",
                "Создать");
        openFile=initButton("open","openhover", "openpressed","Открыть");
        saveFile = initButton("save", "savehover", "savepressed",
                "Сохранить");
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
}
