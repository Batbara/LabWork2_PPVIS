package model;

/**
 * Created by Batbara on 25.03.2017.
 */
public class TableRecord {
    private String studentName;
    private String parentName;
    private String workingAddress;
    private String jobPosition;
    private Double workingYears;

    public TableRecord(String stName, String prntName, String workAdd, String job, double wYears) {
        studentName = new String(stName);
        parentName = new String(prntName);
        workingAddress = new String(workAdd);
        jobPosition = new String(job);
        workingYears = wYears;
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
    public double getWorkingYears(){
        return workingYears;
    }
    public void setWorkingYears(double workingYearsToSet){
        workingYears = workingYearsToSet;
    }

}
