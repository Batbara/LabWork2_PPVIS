package model;

/**
 * Created by Batbara on 04.04.2017.
 */
public class Student {
    PersonName studentName;
    PersonName parentName;
    ParentWorkAddress parentWorkAddress;
    ParentJobPosition parentJobPosition;
    ParentWorkExperience workExperience;

    public Student(PersonName studentName, PersonName parentName, ParentWorkAddress workAddress,
                   ParentJobPosition jobPosition, ParentWorkExperience workExperience){
        this.studentName= studentName;
        this.parentName=parentName;
        this.parentWorkAddress = workAddress;
        this.parentJobPosition = jobPosition;
        this.workExperience = workExperience;
    }

    public String getStudentNameString(){
        return studentName.getShortName();
    }
    public String getParentNameString(){
        return parentName.getShortName();
    }
    public String getParentWorkAddressString(){
        return parentWorkAddress.getWorkAddress();
    }
    public String getParentJobPositionString(){
        return parentJobPosition.getParentJobPosition();
    }
    public String getWorkExperienceString(){
        return workExperience.getWorkExperience();
    }

    public PersonName getStudentName(){
        return studentName;
    }
    public PersonName getParentName(){
        return parentName;
    }
    public ParentWorkAddress getParentWorkAddress(){
        return parentWorkAddress;
    }
    public ParentJobPosition getParentJobPosition(){
        return parentJobPosition;
    }
    public ParentWorkExperience getWorkExperience(){
        return workExperience;
    }


}
