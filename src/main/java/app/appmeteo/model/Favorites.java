package app.appmeteo.model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Favorites {

    private List<City> favList = new ArrayList<City>();

    public Favorites() {
    }

    public List<City> getList() {
        return this.favList;
    }

    public void add(City city) {
        this.favList.add(city);
    }

    public Optional<City> get(String name) {
        return favList.stream().filter(c -> c.toString() == name).findAny();
    }

    public void remove(City city) {
        this.favList.remove(city);
    }

    public String toString() {
        String str = "";
        for (City city : favList) {
            str = str + city.toString();
        }
        return str;
    }

    public boolean equals(Object o) {
        if (o == null) return false;
        if (o.getClass() != this.getClass()) return false;
        return this.favList.equals(o);
    }

    public void writeFavorite2File() throws IOException {
        File file = new File("favorite.dat");
        try {
            FileWriter w = new FileWriter(file, false);
            for(City city : getList()) {
                w.write(city.toString() + '\n');
            }
            w.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Favorites readFavoriteFromFile() throws IOException {
        File file = new File("favorite.dat");
        if (!file.isFile() && !file.createNewFile()) {
            throw new IOException("Error creating new file: " + file.getAbsolutePath());
        }
        Favorites fav = new Favorites();
        BufferedReader r = new BufferedReader(new FileReader(file));
        try {
            String line = r.readLine();
            while (line != null) {
                fav.add(new City(line));
                line = r.readLine();
            }
        } finally {
            r.close();
        }
        return fav;
    }

}
