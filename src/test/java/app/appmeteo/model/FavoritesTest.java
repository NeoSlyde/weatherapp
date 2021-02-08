package app.appmeteo.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class FavoritesTest {

    @Test
    public void testEquals(){
        City city1 = new City("Marseille");
        City city2 = new City("marseille");
        Favorites fav = new Favorites();
        fav.add(city1);

        List<City> fav2 = new ArrayList<City>();
        fav2.add(city2);

        assertEquals(fav2, fav.getList());
    }

    @Test
    public void testPrintNotEqual(){
        City city1 = new City("Marseille");
        City city2 = new City("Paris");
        Favorites fav = new Favorites();
        fav.add(city1);

        Favorites fav2 = new Favorites();
        fav2.add(city2);

        assertNotEquals(fav2.toString(), fav.toString());
    }

    @Test
    public void testPrintEqual(){
        City city1 = new City("Marseille");
        City city2 = new City("Marseille");
        Favorites fav = new Favorites();
        fav.add(city1);

        Favorites fav2 = new Favorites();
        fav2.add(city2);

        assertEquals(fav2.toString(), fav.toString());
    }
}
