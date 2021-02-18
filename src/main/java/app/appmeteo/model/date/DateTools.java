package app.appmeteo.model.date;

import java.time.LocalDate;
import java.util.Optional;

public class DateTools {
    public static Optional<LocalDate> parseDate(String dateStr) {
        try {
            int day = Integer.parseInt(dateStr.substring(0, 2));
            int month = Integer.parseInt(dateStr.substring(3, 5));
            int year = Integer.parseInt(dateStr.substring(6, 10));
            if (day < 1 || day > 31 || month < 1 || month > 12)
                throw new IllegalArgumentException();
            return Optional.of(LocalDate.of(year, month, day));
        } catch (IllegalArgumentException e) {
            return Optional.empty();
        } catch (StringIndexOutOfBoundsException e) {
            return Optional.empty();
        }
    }

    public static String getDay(LocalDate date) {
        switch (date.getDayOfWeek()) {
            case MONDAY:    return "Lundi";
            case TUESDAY:   return "Mardi";
            case WEDNESDAY: return "Mercredi";
            case THURSDAY:  return "Jeudi";
            case FRIDAY:    return "Vendredi";
            case SATURDAY:  return "Samedi";
            case SUNDAY:    return "Dimanche";
        }
        return "???";
    }
}
