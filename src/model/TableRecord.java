package model;

/**
 * Created by Batbara on 25.03.2017.
 */
public class TableRecord {
    private StringBuffer studentName;
    private StringBuffer parentName;
    private StringBuffer workingAddress;
    private StringBuffer jobPosition;
    private Double workingYears;

    public TableRecord(StringBuffer stName, StringBuffer prntName, StringBuffer workAdd, StringBuffer job, double wYears) {
        studentName = new StringBuffer(stName);
        parentName = new StringBuffer(prntName);
        workingAddress = new StringBuffer(workAdd);
        jobPosition = new StringBuffer(job);
        workingYears = wYears;
    }
    public  TableRecord (){
    }
    public StringBuffer getStudentName(){
        return studentName;
    }
    public void setStudentName(StringBuffer studentNameToSet){
        studentName = studentNameToSet;
    }

    public StringBuffer getParentName(){
        return parentName;
    }
    public void setParentName(StringBuffer parentNameToSet){
        parentName = parentNameToSet;
    }
    public StringBuffer getWorkingAddress(){
        return workingAddress;
    }
    public void setWorkingAddress(StringBuffer workingAddressToSet){
         workingAddress = workingAddressToSet;
    }

    public StringBuffer getJobPosition(){
        return jobPosition;
    }
    public void setJobPosition(StringBuffer jobPositionToSet){
        jobPosition = jobPositionToSet;
    }
    public double getWorkingYears(){
        return workingYears;
    }
    public void setWorkingYears(double workingYearsToSet){
        workingYears = workingYearsToSet;
    }

}
