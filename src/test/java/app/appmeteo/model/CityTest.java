package app.appmeteo.model;

import org.junit.jupiter.api.Test;

public class CityTest {


    @Test
    public void testEquals() {
        City city = new City("Marseille");
        City city2 = new City("marseille");

        System.out.println(city.equals(city2));
    }

}
