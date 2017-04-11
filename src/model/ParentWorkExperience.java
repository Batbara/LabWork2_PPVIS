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
    public ParentWorkExperience(Integer years, Integer months){
        this.workingYears=years;
        this.workingMonths = months;
    }
    public ParentWorkExperience(Double workExperience){
        //workExperience.
    }
}
