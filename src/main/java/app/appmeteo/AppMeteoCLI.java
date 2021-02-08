package app.appmeteo;

import app.appmeteo.model.weather.MultiTempWeather;
import app.appmeteo.model.weather.WeatherDeserializer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class AppMeteoCLI {
    public static void main(String[] args) throws IOException {

        WeatherDeserializer deserializer = new WeatherDeserializer();
        File jsonFile = new File("./src/test/resources/daily_weather_test.json");
        String fileContent = Files.readString(jsonFile.toPath());
        List<MultiTempWeather> weatherList = deserializer.getDailyWeather(fileContent);


        System.out.println("Bienvenue dans l'application Météo");

        System.out.println("Vous avez exécuté la commande '" + args[0] + "' avec la ville '" + args[1] + "'");

        System.out.println("Entrez une date DD/MM/YYYY: ");
        Scanner scanner = new Scanner(System.in);
        String date = scanner.nextLine();
        int day = Integer.parseInt(date.substring(0, 2));
        int month = Integer.parseInt(date.substring(3, 5));
        int year = Integer.parseInt(date.substring(6, 10));
        //System.out.println(day +" "+ month +" " +year);
        //System.out.printf("%02d/%02d/%04d\n", day, month, year);
        scanner.close();
        Optional<MultiTempWeather> weather = MultiTempWeather.getWeather(weatherList, LocalDate.of(year, month, day));
        if(weather.isPresent()){
            double temp = weather.get().dayTemperature.toKelvins();
            System.out.println(temp);
        }
    }
}
