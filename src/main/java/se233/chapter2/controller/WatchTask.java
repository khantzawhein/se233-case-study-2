package se233.chapter2.controller;

import javafx.scene.control.Alert;
import se233.chapter2.Launcher;
import se233.chapter2.model.Currency;

import java.util.ArrayList;
import java.util.concurrent.Callable;

public class WatchTask implements Callable<Void> {
    @Override
    public Void call() {
        ArrayList<Currency> allCurrency = Launcher.getCurrencyList();
        StringBuilder found = new StringBuilder();
        for (Currency currency : allCurrency) {
            if (currency.getWatchRate() != 0 && currency.getWatchRate() > currency.getCurrent().getRate()) {
                if (found.toString().equals("")) {
                    found = new StringBuilder(currency.getShortCode());
                } else {
                    found.append(" and ").append(currency.getShortCode());
                }
            }
        }
        if (!found.toString().equals("")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle(null);
            alert.setHeaderText(null);
            if (found.toString().length() > 3) {
                alert.setContentText("The rates of " + found + " are lower than your watch rate!");
            } else {
                alert.setContentText("The rate of " + found + " is lower than your watch rate!");
            }
            alert.showAndWait();
        }
        return null;
    }
}
