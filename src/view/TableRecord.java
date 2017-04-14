package view;
import model.Student;
import java.util.Vector;
/**
 * Created by Batbara on 25.03.2017.
 */
public class TableRecord {
    private Vector <String> tableRecord;



    public TableRecord (Student student){
        tableRecord = new Vector<>();

        String studentName = student.getStudentNameString();
        String parentName = student.getParentNameString();
        String workingAddress = student.getParentWorkAddressString();
        String jobPosition = student.getParentJobPositionString();
        String workingYears = student.getWorkExperienceString();

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
