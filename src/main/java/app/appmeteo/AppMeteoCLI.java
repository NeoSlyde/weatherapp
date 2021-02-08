package app.appmeteo;

import app.appmeteo.controller.OpenWeatherMapAPI;
import app.appmeteo.model.City;
import app.appmeteo.model.weather.MultiTempWeather;
import app.appmeteo.model.weather.WeatherDeserializer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class AppMeteoCLI {
    public static void main(String[] args) throws IOException {

        OpenWeatherMapAPI oAPI = new OpenWeatherMapAPI("0d2e378a4ce98b9fc40278ffe56e1b76");
        List<MultiTempWeather> weatherList = oAPI.fetchDailyWeather(new City(args[1]));


        System.out.println("Voici la météo à " + args[1] + " le date: " + args[2]);

        //System.out.println("Vous avez exécuté la commande '" + args[0] + "' avec la ville '" + args[1] + "' avec la date '" + args[2]);

        /*
        System.out.println("Entrez une date DD/MM/YYYY: ");
        Scanner scanner = new Scanner(System.in);
        String date = scanner.nextLine();
        int day = Integer.parseInt(date.substring(0, 2));
        int month = Integer.parseInt(date.substring(3, 5));
        int year = Integer.parseInt(date.substring(6, 10));
        //System.out.println(day +" "+ month +" " +year);
        //System.out.printf("%02d/%02d/%04d\n", day, month, year);
        scanner.close();

         */

        String date = args[2];
        int day = Integer.parseInt(date.substring(0, 2));
        int month = Integer.parseInt(date.substring(3, 5));
        int year = Integer.parseInt(date.substring(6, 10));
        Optional<MultiTempWeather> weather = MultiTempWeather.getWeather(weatherList, LocalDate.of(year, month, day));

        if(weather.isPresent()) {
            System.out.printf(" - Matin:   %d °C\n", (int) weather.get().morningTemperature.toCelcius());
            System.out.printf(" - Jour:    %d °C\n", (int) weather.get().dayTemperature.toCelcius());
            System.out.printf(" - Soir:    %d °C\n", (int) weather.get().eveningTemperature.toCelcius());
            System.out.printf(" - Nuit:    %d °C\n", (int) weather.get().nightTemperature.toCelcius());
            System.out.printf(" - Minimum: %d °C\n", (int) weather.get().minTemperature.toCelcius());
            System.out.printf(" - Maximum: %d °C\n", (int) weather.get().maxTemperature.toCelcius());
        }
        else{
            System.out.println("Météo introuvable");
        }
    }

}
