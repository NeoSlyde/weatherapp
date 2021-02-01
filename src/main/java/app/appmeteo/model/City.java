package app.appmeteo.model;

public class City {

    String name;

    public City(String name) {
        this.name = name;
    }

    public boolean equals(Object o) {
        if (o == null) return false;
        if (o.getClass() != this.getClass()) return false;
        return this.name.equalsIgnoreCase(((City)o).name);
    }


}
