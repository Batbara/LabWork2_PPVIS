package view;

import controller.DataController;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * Created by Batbara on 15.04.2017.
 */
public class Paging extends TableView{

    int RECORDS_ON_PAGE = 10;
    private JButton nextPageButton;
    private JButton prevPageButton;
    private JButton lastPageButton;
    private JButton firstPageButton;
    private JLabel pagingStatus;
    Boolean isNoIcon;
    int currentPage;

    private Vector<Vector<Vector<String>>> rowsInPages;

    public Paging(){
        super();
        pagingStatus = new JLabel("1 из 1");
        currentPage=0;
        rowsInPages = new Vector<>();
        rowsInPages.add(0,new Vector<>(10));

        nextPageButton = initButton("nextno","Вперед");
        prevPageButton = initButton("prevno","Назад");
        firstPageButton = initButton("backwardno","В начало");
        lastPageButton = initButton("forwardno","В конец");

        setButtonsState();
        //splitInPages();

        setButtonsListeners();
    }
    private JButton initButton (String noFileName, String tipText){
        isNoIcon = true;
        String noImgLocation = "/img/"+noFileName+".png";

        ImageIcon noImage = new ImageIcon(this.getClass().getResource(noImgLocation));


        JButton button = new JButton();

        button.setIcon(noImage);
        button.setBorder(null);
        button.setBackground(null);
        button.setContentAreaFilled(false);
        button.setToolTipText(tipText);

        return button;
    }
    private ImageIcon getImageIcon(String fileName){
        String iconLocation = "/img/"+fileName+".png";
        return new ImageIcon(this.getClass().getResource(iconLocation));
    }
    private void setButtonsState(){
        nextPageButton.getModel().addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                ButtonModel model = (ButtonModel) e.getSource();
                int lastPage = rowsInPages.size() - 1;

                if (currentPage == lastPage) {
                    if (model.isRollover() || model.isPressed()) {
                        nextPageButton.setIcon(getImageIcon("nextno"));
                    }
                    return;
                }
                if (model.isRollover()) {
                    nextPageButton.setIcon(getImageIcon("nexthover"));
                }
                else
                    nextPageButton.setIcon(getImageIcon("next"));
                if (model.isPressed()) {
                    nextPageButton.setIcon(getImageIcon("nextpressed"));
                }
            }
        });
        lastPageButton.getModel().addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                ButtonModel model = (ButtonModel) e.getSource();
                int lastPage = rowsInPages.size() - 1;

                if (currentPage == lastPage) {
                    if (model.isRollover() || model.isPressed()) {
                        lastPageButton.setIcon(getImageIcon("forwardno"));
                    }
                    return;
                }
                if (model.isRollover()) {
                        lastPageButton.setIcon(getImageIcon("forwardhover"));
                }
                else
                    lastPageButton.setIcon(getImageIcon("forward"));
                if (model.isPressed()) {
                    lastPageButton.setIcon(getImageIcon("forwardpressed"));
                }
            }
        });
        prevPageButton.getModel().addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                ButtonModel model = (ButtonModel) e.getSource();

                if (currentPage == 0) {
                    if (model.isRollover() || model.isPressed()) {
                        prevPageButton.setIcon(getImageIcon("prevno"));
                    }
                    return;
                }
                if (model.isRollover()) {
                    prevPageButton.setIcon(getImageIcon("prevhover"));
                } else
                    prevPageButton.setIcon(getImageIcon("prev"));
                if (model.isPressed()) {
                    prevPageButton.setIcon(getImageIcon("prevpressed"));
                }
            }
        });
        firstPageButton.getModel().addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                ButtonModel model = (ButtonModel) e.getSource();

                if (currentPage == 0) {
                    if (model.isRollover() || model.isPressed()) {
                        firstPageButton.setIcon(getImageIcon("backwardno"));
                    }
                    return;
                }
                if (model.isRollover()) {
                    firstPageButton.setIcon(getImageIcon("backwardhover"));
                } else
                    firstPageButton.setIcon(getImageIcon("backward"));
                if (model.isPressed()) {
                    firstPageButton.setIcon(getImageIcon("backwardpressed"));
                }
            }
        });
    }
    public JPanel makeControlButtonsPanel(){
        JPanel holdingPanel = new JPanel(new FlowLayout());
        holdingPanel.add(firstPageButton);
        holdingPanel.add(prevPageButton);
        holdingPanel.add(pagingStatus);
        holdingPanel.add(nextPageButton);
        holdingPanel.add(lastPageButton);
        return holdingPanel;
    }
    public void clearAllRows(){
        rowsInPages.clear();
        currentPage=0;
        rowsInPages.add(0,new Vector<>(10));
        clearModel();
    }
    private void clearModel(){
        DefaultTableModel model = (DefaultTableModel) this.getModel();
        int tableRows = model.getRowCount();
        for(int iterator = tableRows - 1; iterator >=0; iterator--) {
            model.removeRow(iterator);
        }
    }
    public void addRowToTable(Vector<String> rowToAdd){

        int lastPage = rowsInPages.size()-1;
        int newPageIndex = lastPage;
        //for (int pages = 0; pages<numberOfPages; pages++){

            Vector rowsAtPage = rowsInPages.get(lastPage);
            //was: RECORDS_ON_PAGE - 1
            if(rowsAtPage.size() == RECORDS_ON_PAGE) {

                rowsInPages.add(new Vector<Vector<String>>());
                newPageIndex = rowsInPages.size()-1;
            }
                rowsInPages.get(newPageIndex).add(rowToAdd);
        showCurrentPage();
    }
    public void showCurrentPage(){
        clearModel();
        DefaultTableModel model = (DefaultTableModel) this.getModel();
        Vector rowsAtPage = rowsInPages.get(currentPage);

        for(int row =0; row<rowsAtPage.size(); row++){
            Object dataVector = rowsAtPage.get(row);
            model.addRow((Vector)dataVector);
        }
        int pageToShow = currentPage+1;
        pagingStatus.setText(pageToShow + " из " + rowsInPages.size());
        if(currentPage!=rowsInPages.size()-1){
            nextPageButton.setIcon(getImageIcon("next"));
            lastPageButton.setIcon(getImageIcon("forward"));
        }
        else if(currentPage==rowsInPages.size()-1){
            nextPageButton.setIcon(getImageIcon("nextno"));
            lastPageButton.setIcon(getImageIcon("forwardno"));
        }

            if(currentPage==0){
                prevPageButton.setIcon(getImageIcon("prevno"));
                firstPageButton.setIcon(getImageIcon("backwardno"));
            }
            if(currentPage!=0){
            prevPageButton.setIcon(getImageIcon("prev"));
            firstPageButton.setIcon(getImageIcon("backward"));
            }
    }
   
    private void setButtonsListeners(){
        nextPageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(currentPage!= rowsInPages.size()-1) {
                    currentPage++;
                    showCurrentPage();
                }
            }
        });
        prevPageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(currentPage!= 0) {
                    currentPage--;
                    showCurrentPage();
                }
            }
        });
        firstPageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentPage=0;
                showCurrentPage();
            }
        });
        lastPageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentPage=rowsInPages.size()-1;
                showCurrentPage();
            }
        });
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
    public int getNumberOfPages(){
        return rowsInPages.size();
    }

    public void setRECORDS_ON_PAGE(int RECORDS_ON_PAGE) {
        this.RECORDS_ON_PAGE = RECORDS_ON_PAGE;
    }
}
