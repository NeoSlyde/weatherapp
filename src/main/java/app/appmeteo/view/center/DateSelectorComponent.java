package app.appmeteo.view.center;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import app.appmeteo.model.City;
import app.appmeteo.model.date.DateTools;
import app.appmeteo.view.AppScene;
import app.appmeteo.view.misc.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;

public class DateSelectorComponent extends HBox {
    private DayAndDate dayAndDate = new DayAndDate();
    private LocalDate date = null;
    int wonki = 0;

    public DateSelectorComponent(Scene scene, AppScene appScene) {
        this.setAlignment(Pos.CENTER);
        this.setSpacing(15);
        this.setPadding(new Insets(40, 0, 0, 0));

        setDate(LocalDate.now());

        ColorAdjust adjust = new ColorAdjust(); adjust.setBrightness(-.8); adjust.setSaturation(-1);



        ArrowButton prevButton = new ArrowButton(scene, () -> {
            wonki--;
            if(wonki == 0){
                ((ArrowButton)(this.getChildren().get(0))).setClickable(false);
            }
            if(wonki <= 7){
                ((ArrowButton)(this.getChildren().get(2))).setClickable(true);
            }
            setDate(getDate().get().minusDays(1));
            String selectedCity = appScene.getCity();

            Optional<LocalDate> date = appScene.getCenterDate();
            if(date.isPresent()){
                date.ifPresent(d -> appScene.setWeather(new City(selectedCity), d));
                appScene.activate();
            }


        }, -.7);
        prevButton.img.setEffect(adjust);
        prevButton.setPadding(new Insets(15, 0, 0, 0));

        ArrowButton nextButton = new ArrowButton(scene, () -> {
            wonki++;
            if(wonki == 7){
                ((ArrowButton)(this.getChildren().get(2))).setClickable(false);
            }
            if(wonki >= 0){
                ((ArrowButton)(this.getChildren().get(0))).setClickable(true);
            }
            appScene.activate();
            setDate(getDate().get().plusDays(1));
            String selectedCity = appScene.getCity();

            //Optional<LocalDate> date = Optional.empty();
            Optional<LocalDate> date = appScene.getCenterDate();
            date.ifPresent(d -> {
                appScene.setWeather(new City(selectedCity), d);
            });

        }, .7);
        nextButton.img.setEffect(adjust);
        nextButton.setPadding(new Insets(-15, 0, 0, 0));
        
        this.getChildren().add(prevButton);
        this.getChildren().add(dayAndDate);
        this.getChildren().add(nextButton);
        prevButton.setClickable(false);

    }


    public void setDate(LocalDate date) {
        this.date = date;
        dayAndDate.dayLabel.setText(DateTools.getDay(date));
        dayAndDate.dateLabel.setText(date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
    }
    public Optional<LocalDate> getDate() {
        return Optional.ofNullable(date);
    }

    private static class DayAndDate extends VBox {
        Label dayLabel = new AppLabel("Jour", "day");
        Label dateLabel = new AppLabel("jj/MM/aaaa", "date");



        DayAndDate() {
            this.setPrefWidth(270);
            this.setPrefHeight(120);
            this.setSpacing(-10);

            dayLabel.setAlignment(Pos.CENTER);
            dayLabel.setTextFill(Paint.valueOf("#3a3a3a"));
            this.getChildren().add(dayLabel);

            dateLabel.setAlignment(Pos.CENTER);
            dateLabel.setTextFill(Paint.valueOf("#3a3a3a"));
            this.getChildren().add(dateLabel);
        }
    }
}
