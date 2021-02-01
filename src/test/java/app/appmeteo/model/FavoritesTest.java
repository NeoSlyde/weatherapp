package app.appmeteo.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

}
