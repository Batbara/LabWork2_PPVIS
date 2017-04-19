package view;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Batbara on 18.04.2017.
 */
public class Page {
    private int RECORDS_ON_PAGE;
    private List<TableRecord> pageContent;
    public Page(int recordsOnPage){
        pageContent = new ArrayList<>();
        RECORDS_ON_PAGE = recordsOnPage;
    }
    public boolean add(TableRecord recordToAdd){
        if(pageContent.size()!=RECORDS_ON_PAGE) {
            pageContent.add(recordToAdd);
            return true;
        }
        return false;

    }

    public List<TableRecord> getPageContent() {
        return pageContent;
    }
    public int getNumberOfPageRecords(){
        return pageContent.size();
    }

    public void removeRecord(TableRecord recordToRemove){
        for (TableRecord record : pageContent) {
            if (record.equals(recordToRemove)) {
                pageContent.remove(record);
                return;
            }
        }
    }

    public List<List<String>> getListOfRows(){
        List<List<String>> listOfRows =  new ArrayList<>();
        for (TableRecord recordToConvert : pageContent){
            listOfRows.add(recordToConvert.getRecordData());
        }
        return listOfRows;
    }
    public boolean isEmpty(){
        return pageContent.isEmpty();
    }

}
