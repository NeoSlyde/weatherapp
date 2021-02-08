package app.appmeteo;

import app.appmeteo.controller.OpenWeatherMapAPI;
import app.appmeteo.model.City;
import app.appmeteo.model.weather.CurrentWeather;
import app.appmeteo.model.weather.MultiTempWeather;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class AppMeteoCLI {
    public static void main(String[] args) throws IOException {
        /*
         * Input sanitizer
         */
        if (args.length != 1 && args.length != 2) {
            System.out.println("Erreur: Nombre d'arguments invalides");
            System.out.println("Argument 1: Ville");
            System.out.println("Argument 2 (Optional): Date (jj/MM/aaaa)");
            System.exit(-1);
        }

        Optional<LocalDate> date = Optional.empty();
        if (args.length == 2) {
            try {
                int day = Integer.parseInt(args[1].substring(0, 2));
                int month = Integer.parseInt(args[1].substring(3, 5));
                int year = Integer.parseInt(args[1].substring(6, 10));
                if (day < 1 || day > 31 || month < 1 || month > 12)
                    throw new IllegalArgumentException();
                date = Optional.of(LocalDate.of(year, month, day));
            } catch (IllegalArgumentException e) {
                System.out.println("Erreur: Date invalide");
                System.out.println("Format: jj/MM/aaaa");
                System.exit(-1);
            }
        }

        /*
         * Program start
         */

        OpenWeatherMapAPI oAPI = new OpenWeatherMapAPI("0d2e378a4ce98b9fc40278ffe56e1b76");

        if (date.isPresent()) { 
            // i.e if the user specified the date
            System.out.println("Voici la météo à " + args[0] + " le " + args[1] + ":");
            List<MultiTempWeather> weatherList = oAPI.fetchDailyWeather(new City(args[0]));
            Optional<MultiTempWeather> weather = MultiTempWeather.getWeather(weatherList, date.get());

            weather.ifPresentOrElse(w -> {
                System.out.println(" - Ciel:    " + w.description);
                System.out.println(" - Matin:   " + w.morningTemperature);
                System.out.println(" - Jour:    " + w.dayTemperature);
                System.out.println(" - Soir:    " + w.eveningTemperature);
                System.out.println(" - Nuit:    " + w.nightTemperature);
                System.out.println(" - Minimum: " + w.minTemperature);
                System.out.println(" - Maximum: " + w.maxTemperature);
            }, () -> System.out.println("Météo introuvable"));
        } else {
            // i.e if the user didn't specify the date
            System.out.println("Voici la météo à " + args[0] + " aujourd'hui:");
            try {
                CurrentWeather weather = oAPI.fetchCurrentWeather(new City(args[0]));
                System.out.println(" - Temperature: " + weather.temperature.toString());
                System.out.println(" - Ciel: " + weather.description);
            } catch (FileNotFoundException e) {
                System.out.println("Ville introuvable");
            }
        }
    }
}
