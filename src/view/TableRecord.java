package view;
import model.Student;
import model.Worker;

import java.util.List;
import java.util.Objects;
import java.util.Vector;
/**
 * Created by Batbara on 25.03.2017.
 */
public class TableRecord extends Object{
    private List <String> tableRecord;



    public TableRecord (Student student){
        tableRecord = new Vector<>();
        Worker parentWorker = student.getStudentParent().getWorkerData();

        String studentName = student.getFullName();
        String parentName = student.getStudentParent().getFullName();

        String workingAddress = parentWorker.getFullAddress();
        String jobPosition = parentWorker.getJobPosition();
        String workingYears = parentWorker.getWorkingExperience();

        tableRecord.add(studentName);
        tableRecord.add(parentName);
        tableRecord.add(workingAddress);
        tableRecord.add(jobPosition);
        tableRecord.add(workingYears);
    }

    public List<String> getRecordData(){
        return tableRecord;
    }
    public boolean equals(TableRecord recordToCompare){
        List<String> recordData = this.getRecordData();
        List<String> comparedData = recordToCompare.getRecordData();
        for(String dataCell : recordData) {
            if( !comparedData.get(recordData.indexOf(dataCell)).equals(dataCell) )
                return false;
        }
        return true;

    }

}
