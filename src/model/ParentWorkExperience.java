package model;

/**
 * Created by Batbara on 08.04.2017.
 */
public class ParentWorkExperience {
    Integer workingYears;
    Integer workingMonths;

    public ParentWorkExperience (){
        this.workingYears=0;
        this.workingMonths=0;
    }
    public ParentWorkExperience(String years, String months){
        int workingYears = Integer.parseInt(years);
        int workingMonths = Integer.parseInt(months);
        if (workingMonths >= 12) {
             workingYears = workingYears + workingMonths % 12;
             workingMonths /= 12;
        }
        this.workingYears=workingYears;
        this.workingMonths = workingMonths;
    }
    public String getWorkExperience(){
        return Integer.toString(workingYears)+"."+Integer.toString(workingMonths);
    }

}
