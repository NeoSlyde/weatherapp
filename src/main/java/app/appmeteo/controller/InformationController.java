package app.appmeteo.controller;

public class InformationController {
    private String currentCity;
    private String currentDate;


    public String getCity(){
        return currentCity;
    }

    public String getDate(){
        return currentDate;
    }

    public void setCity(String city){
        this.currentCity=city;
    }

    public void setDate(String date){
        this.currentDate=date;
    }
}
