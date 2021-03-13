package app.appmeteo.model;

public class City {
    private String name;

    public City(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (o.getClass() != City.class) return false;
        return this.name.equalsIgnoreCase(((City)o).name);
    }
    @Override
    public int hashCode() {
        return toString().toLowerCase().hashCode();
    }


}
