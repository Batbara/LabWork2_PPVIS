package view;
import model.Student;
import model.Worker;

import java.util.Vector;
/**
 * Created by Batbara on 25.03.2017.
 */
public class TableRecord {
    private final Vector <String> tableRecord;



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

    public Vector<String> getRecordData(){
        return tableRecord;
    }

}
