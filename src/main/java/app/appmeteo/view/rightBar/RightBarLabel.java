package app.appmeteo.view.rightBar;

<<<<<<< HEAD
import app.appmeteo.controller.OpenWeatherMapAPI;
import app.appmeteo.model.City;
import app.appmeteo.model.weather.MultiTempWeather;
=======
import app.appmeteo.model.City;
>>>>>>> 0916ad0fffb668cbc66df114f4ffa17fe10efecf
import app.appmeteo.view.misc.AppLabel;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

<<<<<<< HEAD
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

=======
>>>>>>> 0916ad0fffb668cbc66df114f4ffa17fe10efecf

public class RightBarLabel extends VBox {

    private Label cityLabel;
<<<<<<< HEAD
=======
    private City city;
>>>>>>> 0916ad0fffb668cbc66df114f4ffa17fe10efecf
    private Label temperatureLabel = new AppLabel("","part-of-the-day-temperature");

    public RightBarLabel(City city){
        this.cityLabel = new AppLabel(city.toString(), "city-label");
<<<<<<< HEAD

        LocalDate currentDate = LocalDate.now();
        OpenWeatherMapAPI oAPI = new OpenWeatherMapAPI("0d2e378a4ce98b9fc40278ffe56e1b76");
        List<MultiTempWeather> weatherList = oAPI.fetchDailyWeather(city);

        Optional<MultiTempWeather> weather = MultiTempWeather.getWeather(weatherList, currentDate);
        weather.ifPresentOrElse(w -> {
            setTemperature(w.dayTemperature.toInt());
        }, () -> System.out.println("M�t�o introuvable"));

=======
        this.city = city;

        setTemperature(8);
>>>>>>> 0916ad0fffb668cbc66df114f4ffa17fe10efecf
        cityLabel.setAlignment(Pos.CENTER);
        temperatureLabel.setPadding(new Insets(20, 0, 10, 150));

        this.getChildren().add(cityLabel);
        this.getChildren().add(temperatureLabel);
    }

    public void setTemperature(int temperature) {
        temperatureLabel.setText(temperature + "°C");
    }
}
