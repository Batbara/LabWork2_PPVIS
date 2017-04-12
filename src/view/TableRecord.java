package view;
import model.Student;
import java.util.Vector;
/**
 * Created by Batbara on 25.03.2017.
 */
public class TableRecord {
    private Vector <String> tableRecord;
    private String studentName;
    private String parentName;
    private String workingAddress;
    private String jobPosition;
    private String workingYears;


    public TableRecord (Student student){
        this.studentName = student.getStudentName();
        this.parentName = student.getParentName();
        this.workingAddress = student.getParentWorkAddress();
        this.jobPosition = student.getParentJobPosition();
        this.workingYears = student.getWorkExperience();
        tableRecord = new Vector<>();
        tableRecord.add(studentName);
        tableRecord.add(parentName);
        tableRecord.add(workingAddress);
        tableRecord.add(jobPosition);
        tableRecord.add(workingYears);
    }
   public  TableRecord (){
    }
    public String getStudentName(){
        return studentName;
    }
    public void setStudentName(String studentNameToSet){
        studentName = studentNameToSet;
    }

    public String getParentName(){
        return parentName;
    }
    public void setParentName(String parentNameToSet){
        parentName = parentNameToSet;
    }
    public String getWorkingAddress(){
        return workingAddress;
    }
    public void setWorkingAddress(String workingAddressToSet){
         workingAddress = workingAddressToSet;
    }

    public String getJobPosition(){
        return jobPosition;
    }
    public void setJobPosition(String jobPositionToSet){
        jobPosition = jobPositionToSet;
    }
    public String getWorkingYears(){
        return workingYears;
    }
    public void setWorkingYears(String workingYearsToSet){
        workingYears = workingYearsToSet;
    }
    public Vector<String> getRecordData(){
        return tableRecord;
    }

}
