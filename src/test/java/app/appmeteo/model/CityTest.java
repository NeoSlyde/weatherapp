package app.appmeteo.model;

public class CityTest {

    public static void main(String[] args) {
        City city = new City("Marseille");
        City city2 = new City("marseille");

        System.out.println(city.equals(city2));
    }

}
