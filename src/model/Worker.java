package model;

/**
 * Created by Batbara on 14.04.2017.
 */
public class Worker {

   private String cityOfWork;
   private String streetOfWork;
   private Integer buildingNumberOfWork;

   private Integer workingYears;
   private Integer workingMonths;

   private String jobPosition;

    public Worker(){
        cityOfWork="";
        streetOfWork="";
        buildingNumberOfWork=null;
        workingMonths=null;
        workingYears=null;
    }

    public String getFullAddress(){
        return "г. "+cityOfWork+", " + streetOfWork +" д. " + Integer.toString(buildingNumberOfWork);
    }
    public String getWorkingExperience(){
        return Integer.toString(workingYears)+"."+Integer.toString(workingMonths);
    }
    public String getCityOfWork() {
        return cityOfWork;
    }

    public void setCityOfWork(String cityOfWork) {
        this.cityOfWork = cityOfWork;
    }

    public String getStreetOfWork() {
        return streetOfWork;
    }

    public void setStreetOfWork(String streetOfWork) {
        this.streetOfWork = streetOfWork;
    }

    public Integer getBuildingNumberOfWork() {
        return buildingNumberOfWork;
    }

    public void setBuildingNumberOfWork(Integer buildingNumberOfWork) {
        this.buildingNumberOfWork = buildingNumberOfWork;
    }

    public String getJobPosition() {
        return jobPosition;
    }

    public void setJobPosition(String jobPosition) {
        this.jobPosition = jobPosition;
    }

    public Integer getWorkingYears() {
        return workingYears;
    }

    public void setWorkingYears(Integer workingYears) {
        this.workingYears = workingYears;
    }

    public Integer getWorkingMonths() {
        return workingMonths;
    }

    public void setWorkingMonths(Integer workingMonths) {
        if (workingMonths >= 12) {
            workingYears = workingYears + workingMonths % 12;
            workingMonths /= 12;
        }
        this.workingMonths = workingMonths;
    }
}
