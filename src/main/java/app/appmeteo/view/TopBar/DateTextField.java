package app.appmeteo.view.TopBar;

import javafx.scene.control.TextField;

public class DateTextField extends TextField {
    public DateTextField() {
        this.setPromptText("jj/MM/aaaa");
        this.textProperty().addListener((o, oldValue, newValue) -> {
            // Input Sanitizer
            if (!newValue.isEmpty()) {
                char lastChar = newValue.charAt(newValue.length() - 1);
                if (!Character.isDigit(lastChar) && lastChar != '/') {
                    this.setText(oldValue);
                    return;
                }
            }
            if (newValue.length() == 2) {
                int day = Integer.parseInt(newValue.substring(0, 2));
                if (day <= 0 || day > 31) {
                    this.setText(oldValue);
                    return;
                }
            }
            if (newValue.length() == 5) {
                int month = Integer.parseInt(newValue.substring(3, 5));
                if (month <= 0 || month > 12) {
                    this.setText(oldValue);
                    return;
                }
            }

            // Auto complete the slashes in jj/mm/aaaa
            if (newValue.length() == 2 && oldValue.length() == 1)
                this.setText(newValue + "/");
            else if (newValue.length() == 5 && oldValue.length() == 4)
                this.setText(newValue + "/");
            else if (newValue.length() > 10 && oldValue.length() == 10)
                this.setText(oldValue);
        });
        this.getStyleClass().add("search");
    }
}
