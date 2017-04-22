package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by Batbara on 15.04.2017.
 */
public class Paging extends TableView {

    private int RECORDS_ON_PAGE;
    private final JButton nextPageButton;
    private final JButton prevPageButton;
    private final JButton lastPageButton;
    private final JButton firstPageButton;
    private final JLabel pagingStatus;
    private int currentPageNum;
    private JTextField recordsOnPageField;
    private JLabel numberOfRecordsField;
    private int numberOfRecords;

    private List<Page> listOfPages;

    public Paging(int recordsOnPage) {
        super();
        pagingStatus = new JLabel("1 из 1");
        numberOfRecordsField = new JLabel("из 0");
        recordsOnPageField = new JTextField(2);
        currentPageNum = 0;
        numberOfRecords = 0;
        RECORDS_ON_PAGE = recordsOnPage;
        recordsOnPageField.setText("0");


        listOfPages = new ArrayList<>();

        nextPageButton = initButton("nextno", "Вперед");
        prevPageButton = initButton("prevno", "Назад");
        firstPageButton = initButton("backwardno", "В начало");
        lastPageButton = initButton("forwardno", "В конец");

        setButtonsState();

        setButtonsListeners();
        setTextFieldListener();
    }

    private JButton initButton(String noFileName, String tipText) {
        Boolean isNoIcon = true;
        String noImgLocation = "/img/" + noFileName + ".png";

        ImageIcon noImage = new ImageIcon(this.getClass().getResource(noImgLocation));

        JButton button = new JButton();

        button.setIcon(noImage);
        button.setBorder(null);
        button.setBackground(null);
        button.setContentAreaFilled(false);
        button.setToolTipText(tipText);

        return button;
    }

    private ImageIcon getImageIcon(String fileName) {
        String iconLocation = "/img/" + fileName + ".png";
        return new ImageIcon(this.getClass().getResource(iconLocation));
    }

    private void setButtonsState() {
        nextPageButton.getModel().addChangeListener(e -> {
            ButtonModel model = (ButtonModel) e.getSource();
            int lastPage = listOfPages.size() - 1;

            if (currentPageNum == lastPage || listOfPages.isEmpty()) {
                if (model.isRollover() || model.isPressed()) {
                    nextPageButton.setIcon(getImageIcon("nextno"));
                }
                return;
            }
            if (model.isRollover()) {
                nextPageButton.setIcon(getImageIcon("nexthover"));
            } else
                nextPageButton.setIcon(getImageIcon("next"));
            if (model.isPressed()) {
                nextPageButton.setIcon(getImageIcon("nextpressed"));
            }
        });
        lastPageButton.getModel().addChangeListener(e -> {
            ButtonModel model = (ButtonModel) e.getSource();
            int lastPage = listOfPages.size() - 1;

            if (currentPageNum == lastPage || listOfPages.isEmpty()) {
                if (model.isRollover() || model.isPressed()) {
                    lastPageButton.setIcon(getImageIcon("forwardno"));
                }
                return;
            }
            if (model.isRollover()) {
                lastPageButton.setIcon(getImageIcon("forwardhover"));
            } else
                lastPageButton.setIcon(getImageIcon("forward"));
            if (model.isPressed()) {
                lastPageButton.setIcon(getImageIcon("forwardpressed"));
            }
        });
        prevPageButton.getModel().addChangeListener(e -> {
            ButtonModel model = (ButtonModel) e.getSource();

            if (currentPageNum == 0 || listOfPages.isEmpty()) {
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

            if (currentPageNum == 0 || listOfPages.isEmpty()) {
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

    public JPanel makeControlButtonsPanel() {
        JPanel holdingPanel = new JPanel(new FlowLayout());
        holdingPanel.add(new JLabel("Записи на странице: "), FlowLayout.LEFT);
        holdingPanel.add(recordsOnPageField);
        holdingPanel.add(numberOfRecordsField);
        holdingPanel.add(firstPageButton);
        holdingPanel.add(prevPageButton);
        holdingPanel.add(pagingStatus);
        holdingPanel.add(nextPageButton);
        holdingPanel.add(lastPageButton);
        return holdingPanel;
    }

    public void clearAllRows() {
        listOfPages.clear();
        currentPageNum = 0;
        clearModel();

    }

    private void clearModel() {
        DefaultTableModel model = (DefaultTableModel) this.getModel();
        int tableRows = model.getRowCount();
        for (int iterator = tableRows - 1; iterator >= 0; iterator--) {
            model.removeRow(iterator);
        }
    }

    public void addRecordToTable(TableRecord recordToAdd) {
        if (listOfPages.isEmpty()) {
            listOfPages.add(new Page(RECORDS_ON_PAGE));
        }
        Page currentPage = listOfPages.get(listOfPages.size() - 1);

        boolean isRecordAdded = currentPage.add(recordToAdd);
        if (!isRecordAdded) {
            Page newPage = new Page(RECORDS_ON_PAGE);
            newPage.add(recordToAdd);
            listOfPages.add(newPage);
        }
        numberOfRecords++;
        showCurrentPage();
    }

    public void deleteRecordFromTable(TableRecord recordToDelete) {

        for (Page pages : listOfPages) {
            for (TableRecord record : pages.getPageContent()) {
                if (record.equals(recordToDelete)) {
                    pages.removeRecord(recordToDelete);
                    shiftPages();
                    showCurrentPage();
                    if(numberOfRecords!=0)
                        numberOfRecords--;
                    return;
                }
            }
        }
    }

    private void shiftPages() {
        int capacity = listOfPages.size() - 1;
        for (int currentPageNum = 0; currentPageNum < capacity; currentPageNum++) {
            Page currentPage = listOfPages.get(currentPageNum);
            Page nextPage;

            nextPage = listOfPages.get(currentPageNum + 1);

            int numOfRowsAtPage = currentPage.getNumberOfPageRecords();
            if (currentPage.isEmpty()) {
                listOfPages.remove(currentPageNum);
                continue;
            }
            if (nextPage.isEmpty()) {
                listOfPages.remove(nextPage);
                continue;
            }
            if (numOfRowsAtPage < RECORDS_ON_PAGE) {
                TableRecord bitToFill = nextPage.getPageContent().get(0);
                currentPage.add(bitToFill);
                nextPage.removeRecord(bitToFill);
            }
        }
    }

    public void showCurrentPage() {
        clearModel();
        if (listOfPages.isEmpty()) {
            pagingStatus.setText("1 из 1");
            nextPageButton.setIcon(getImageIcon("nextno"));
            lastPageButton.setIcon(getImageIcon("forwardno"));
            prevPageButton.setIcon(getImageIcon("prevno"));
            firstPageButton.setIcon(getImageIcon("backwardno"));
            setButtonsState();
            return;
        }

        Page lastPage = listOfPages.get(listOfPages.size() - 1);
        Page currentPage;
        try {
             currentPage = listOfPages.get(currentPageNum);
        }catch (IndexOutOfBoundsException e){
            System.err.println("IndexOutOfBoundsException caught!");
            currentPage = lastPage;
        }
        if (lastPage.isEmpty())
            listOfPages.remove(lastPage);

        DefaultTableModel model = (DefaultTableModel) this.getModel();
        List<List<String>> rowsAtPage = currentPage.getListOfRows();

        for (Object recordData : rowsAtPage) {
            model.addRow((Vector) recordData);
        }
        int pageToShow = currentPageNum + 1;
        pagingStatus.setText(pageToShow + " из " + listOfPages.size());
        recordsOnPageField.setText(Integer.toString(RECORDS_ON_PAGE));
//        numberOfRecords = 0;
//        for (Page page : listOfPages) {
//            numberOfRecords = numberOfRecords + page.getNumberOfPageRecords();
//        }
        numberOfRecordsField.setText("из " + numberOfRecords);
        calibrateControlButtons();
    }

    private void calibrateControlButtons() {
        if (currentPageNum != listOfPages.size() - 1) {
            nextPageButton.setIcon(getImageIcon("next"));
            lastPageButton.setIcon(getImageIcon("forward"));
        } else if (currentPageNum == listOfPages.size() - 1) {
            nextPageButton.setIcon(getImageIcon("nextno"));
            lastPageButton.setIcon(getImageIcon("forwardno"));
        }

        if (currentPageNum == 0) {
            prevPageButton.setIcon(getImageIcon("prevno"));
            firstPageButton.setIcon(getImageIcon("backwardno"));
        }
        if (currentPageNum != 0) {
            prevPageButton.setIcon(getImageIcon("prev"));
            firstPageButton.setIcon(getImageIcon("backward"));
        }
        int pageToShow = currentPageNum + 1;
        pagingStatus.setText(pageToShow + " из " + listOfPages.size());
    }

    private void setButtonsListeners() {
        nextPageButton.addActionListener(e -> {
            if (currentPageNum != listOfPages.size() - 1) {
                currentPageNum++;
                showCurrentPage();
            }
        });
        prevPageButton.addActionListener(e -> {
            if (currentPageNum != 0) {
                currentPageNum--;
                showCurrentPage();
            }
        });
        firstPageButton.addActionListener(e -> {
            currentPageNum = 0;
            showCurrentPage();
        });
        lastPageButton.addActionListener(e -> {
            currentPageNum = listOfPages.size() - 1;
            showCurrentPage();
        });
    }

    private void setTextFieldListener() {
        recordsOnPageField.addActionListener(e -> {
            String inputText = recordsOnPageField.getText();
            if (inputText == null || Integer.parseInt(inputText)>numberOfRecords ||
                    Integer.parseInt(inputText)<0)
                return;
            setRECORDS_ON_PAGE(Integer.parseInt(inputText));
            rearrangePaging();
            calibrateControlButtons();
            showCurrentPage();
        });
    }

    private void rearrangePaging() {
        List<TableRecord> records = new ArrayList<>();
        for (Page page : listOfPages) {
            for (TableRecord record : page.getPageContent())
                records.add(record);
        }
        int numberOfRecords = records.size();
        int numberOfPages = (int) Math.ceil((double) numberOfRecords / RECORDS_ON_PAGE);
        listOfPages = new ArrayList<>();
        boolean isAbleToAdd = true;
        int lastRecord = 0;
        for (int page = 0; page < numberOfPages; page++) {
            Page pageToAdd = new Page(RECORDS_ON_PAGE);
            if (page==numberOfPages-1 && numberOfRecords%RECORDS_ON_PAGE!=0){
                for (int record = numberOfRecords-numberOfRecords%RECORDS_ON_PAGE; record < numberOfRecords; record++)
                   pageToAdd.add(records.get(record));
                listOfPages.add(pageToAdd);
                break;
            }
            while (isAbleToAdd) {
                for (int record = lastRecord; record < numberOfRecords; record++) {
                    isAbleToAdd = pageToAdd.add(records.get(record));
                    if(!isAbleToAdd) {
                        lastRecord = record;
                        break;
                    }
                      isAbleToAdd=false;
                }
            }
            isAbleToAdd=true;
            listOfPages.add(pageToAdd);
        }
        if(currentPageNum>listOfPages.size()-1)
            currentPageNum = listOfPages.size()-1;
    }

    public void setCurrentPageNum(int currentPageNum) {
        this.currentPageNum = currentPageNum;
    }
    public int getNumberOfPages(){
        return listOfPages.size();
    }

    public void setRECORDS_ON_PAGE(int num) {
        this.RECORDS_ON_PAGE = num;
    }
}
