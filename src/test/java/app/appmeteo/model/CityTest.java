package app.appmeteo.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CityTest {


    @Test
    public void testEquals() {
        City city = new City("Marseille");
        City city2 = new City("marseille");

        assertEquals(city, city2);
    }

}
