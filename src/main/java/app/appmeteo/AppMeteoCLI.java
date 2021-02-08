package app.appmeteo;

import app.appmeteo.controller.OpenWeatherMapAPI;
import app.appmeteo.model.City;
import app.appmeteo.model.weather.MultiTempWeather;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class AppMeteoCLI {
    public static void main(String[] args) throws IOException {
        /*
         * Input sanitizer
         */
        if (args.length != 2) {
            System.out.println("Erreur: Nombre d'arguments invalides");
            System.out.println("Argument 1: Ville");
            System.out.println("Argument 2: Date (jj/MM/aaaa)");
            System.exit(-1);
        }

        String date = args[1];
        int day = 0, month = 0, year = 0;
        try {
            day = Integer.parseInt(date.substring(0, 2));
            month = Integer.parseInt(date.substring(3, 5));
            year = Integer.parseInt(date.substring(6, 10));
            if (day < 1 || day > 31 || month < 1 || month > 12)
                throw new IllegalArgumentException();
        } catch (IllegalArgumentException e) {
            System.out.println("Erreur: Date invalide");
            System.out.println("Format: jj/MM/aaaa");
            System.exit(-1);
        }

        /*
         * Program start
         */

        OpenWeatherMapAPI oAPI = new OpenWeatherMapAPI("0d2e378a4ce98b9fc40278ffe56e1b76");
        List<MultiTempWeather> weatherList = oAPI.fetchDailyWeather(new City(args[0]));

        System.out.println("Voici la météo à " + args[0] + " le " + args[1] + ":");

        Optional<MultiTempWeather> weather = MultiTempWeather.getWeather(weatherList, LocalDate.of(year, month, day));

        weather.ifPresentOrElse((w) -> {
            System.out.printf(" - Matin:   %.0f°C\n", w.morningTemperature.toCelcius());
            System.out.printf(" - Jour:    %.0f°C\n", w.dayTemperature.toCelcius());
            System.out.printf(" - Soir:    %.0f°C\n", w.eveningTemperature.toCelcius());
            System.out.printf(" - Nuit:    %.0f°C\n", w.nightTemperature.toCelcius());
            System.out.printf(" - Minimum: %.0f°C\n", w.minTemperature.toCelcius());
            System.out.printf(" - Maximum: %.0f°C\n", w.maxTemperature.toCelcius());
        }, () -> System.out.println("Météo introuvable"));
    }

}
