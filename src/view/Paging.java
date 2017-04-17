package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by Batbara on 15.04.2017.
 */
public class Paging extends TableView{

    private int RECORDS_ON_PAGE = 10;
    private final JButton nextPageButton;
    private final JButton prevPageButton;
    private final JButton lastPageButton;
    private final JButton firstPageButton;
    private final JLabel pagingStatus;
    private int currentPage;

    private final List<List<List<String>>> rowsInPages;

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
        Boolean isNoIcon = true;
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
        nextPageButton.getModel().addChangeListener(e -> {
            ButtonModel model = (ButtonModel) e.getSource();
            int lastPage = rowsInPages.size() - 1;

            if (currentPage == lastPage || rowsInPages.isEmpty()) {
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
        });
        lastPageButton.getModel().addChangeListener(e -> {
            ButtonModel model = (ButtonModel) e.getSource();
            int lastPage = rowsInPages.size() - 1;

            if (currentPage == lastPage || rowsInPages.isEmpty()) {
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
        });
        prevPageButton.getModel().addChangeListener(e -> {
            ButtonModel model = (ButtonModel) e.getSource();

            if (currentPage == 0 || rowsInPages.isEmpty()) {
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
        });
        firstPageButton.getModel().addChangeListener(e -> {
            ButtonModel model = (ButtonModel) e.getSource();

            if (currentPage == 0 || rowsInPages.isEmpty()) {
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
        //rowsInPages.add(0,new Vector<>(10));
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
        if(rowsInPages.isEmpty())
            rowsInPages.add(0,new Vector<>(10));
        int lastPage = rowsInPages.size()-1;
        int newPageIndex = lastPage;
        //for (int pages = 0; pages<numberOfPages; pages++){

            List rowsAtPage = rowsInPages.get(lastPage);
            //was: RECORDS_ON_PAGE - 1
            if(rowsAtPage.size() == RECORDS_ON_PAGE) {

                rowsInPages.add(new Vector<>());
                newPageIndex = rowsInPages.size()-1;
            }
                rowsInPages.get(newPageIndex).add(rowToAdd);
        showCurrentPage();
    }
    public void deleteRowFromTable(Vector<String> rowToDelete){

        for(int pages =0; pages<rowsInPages.size()-1; pages++){
            List<List<String>> rowsAtPage = rowsInPages.get(pages);
            for(List<String> row : rowsAtPage)
                if(Objects.equals(row, rowToDelete)){
                    rowsAtPage.remove(row);
                    shiftPages();
                    showCurrentPage();
                    return;
                }
        }
    }

    private void shiftPages(){
        int capacity = rowsInPages.size()-1;
        for(int pages =0; pages<capacity; pages++) {
            List<List<String>> currentPage = rowsInPages.get(pages);

            List<List<String>> nextPage = rowsInPages.get(pages + 1);
            int numOfRowsAtPage = currentPage.size();
            if(currentPage.isEmpty()){
                rowsInPages.remove(currentPage);
                capacity--;
                continue;
            }
            if(nextPage.isEmpty()){
                rowsInPages.remove(nextPage);
                capacity--;
                continue;
            }
            if (numOfRowsAtPage < RECORDS_ON_PAGE) {
                List<String> bitToFill = nextPage.get(0);
                currentPage.add(bitToFill);
                nextPage.remove(bitToFill);
            }
        }
    }
    public void showCurrentPage(){
        clearModel();
        if(rowsInPages.isEmpty()){
            pagingStatus.setText("1 из 1");
            nextPageButton.setIcon(getImageIcon("nextno"));
            lastPageButton.setIcon(getImageIcon("forwardno"));
            prevPageButton.setIcon(getImageIcon("prevno"));
            firstPageButton.setIcon(getImageIcon("backwardno"));
            setButtonsState();
            return;
        }
        List<List<String>> lastPage = rowsInPages.get(rowsInPages.size()-1);
        if(lastPage.isEmpty())
            rowsInPages.remove(lastPage);

        DefaultTableModel model = (DefaultTableModel) this.getModel();
        List rowsAtPage = rowsInPages.get(currentPage);

        for (Object dataVector : rowsAtPage) {
            model.addRow((Vector) dataVector);
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
        nextPageButton.addActionListener(e -> {
            if(currentPage!= rowsInPages.size()-1) {
                currentPage++;
                showCurrentPage();
            }
        });
        prevPageButton.addActionListener(e -> {
            if(currentPage!= 0) {
                currentPage--;
                showCurrentPage();
            }
        });
        firstPageButton.addActionListener(e -> {
            currentPage=0;
            showCurrentPage();
        });
        lastPageButton.addActionListener(e -> {
            currentPage=rowsInPages.size()-1;
            showCurrentPage();
        });
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
    public int getNumberOfPages(){
        return rowsInPages.size();
    }

    public void setRECORDS_ON_PAGE() {
        this.RECORDS_ON_PAGE = 3;
    }
}
