package model;

/**
 * Created by Batbara on 04.04.2017.
 */
public class ParentWorkAddress {
    String cityOfWork;
    String streetOfWork;
    Integer buildingNumberOfWork;


    public ParentWorkAddress(String city, String street, String buildingNumber){
        this.cityOfWork=city;
        this.streetOfWork=street;
        this.buildingNumberOfWork=Integer.parseInt(buildingNumber);
    }
    public String getWorkAddress(){
        String workAddress = "г. "+cityOfWork+", " + streetOfWork +" д. " + Integer.toString(buildingNumberOfWork);
        return workAddress;
    }

    public String getCityOfWork() {
        return cityOfWork;
    }

    public String getStreetOfWork() {
        return streetOfWork;
    }

    public Integer getBuildingNumberOfWork() {
        return buildingNumberOfWork;
    }
}
