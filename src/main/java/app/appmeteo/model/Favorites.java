package app.appmeteo.model;

import java.util.ArrayList;
import java.util.List;

public class Favorites {

    private List<City> favList = new ArrayList<City>();

    public Favorites(){
    }

    public List<City> getList(){
        return this.favList;
    }

    public void add(City city){
        this.favList.add(city);
    }

    public void remove(City city){
        this.favList.remove(city);
    }

    public String toString(){
        String str = "";
        for(City city : favList){
            str = str + city.toString();
        }
        return str;
    }

    public boolean equals(Object o) {
        if (o == null) return false;
        if (o.getClass() != this.getClass()) return false;
        return this.favList.equals(o);
    }

}
