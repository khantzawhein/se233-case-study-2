package se233.chapter2.controller;

import javafx.scene.control.TextInputDialog;
import se233.chapter2.Launcher;
import se233.chapter2.model.Currency;
import se233.chapter2.model.CurrencyEntity;

import java.util.ArrayList;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

public class AllEventHandler {
    public static void onRefresh() {
        try {
            Launcher.refreshPane();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void onAdd() {
        try {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Add Currency");
            dialog.setContentText("Currency Code:");
            dialog.setHeaderText(null);
            dialog.setGraphic(null);
            Optional<String> code = dialog.showAndWait();
            if (code.isPresent()) {
                ArrayList<Currency> currency_list = Launcher.getCurrencyList();
                Currency c = new Currency(code.get());
                ArrayList<CurrencyEntity> c_list = FetchData.fetch_range(c.getShortCode(), 8);
                c.setHistorical(c_list);
                c.setCurrent(c_list.get(c_list.size() - 1));
                currency_list.add(c);
                Launcher.setCurrencyList(currency_list);
                Launcher.refreshPane();
            }
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void onDelete(String code) {
        try {
            ArrayList<Currency> currency_list = Launcher.getCurrencyList();
            for (Currency c : currency_list) {
                if (c.getShortCode().equals(code)) {
                    currency_list.remove(c);
                    break;
                }
            }
            Launcher.setCurrencyList(currency_list);
            Launcher.refreshPane();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void onWatch(String code) {
        try {
            ArrayList<Currency> currency_list = Launcher.getCurrencyList();
            Currency currency = null;
            for (Currency c : currency_list) {
                if (c.getShortCode().equals(code)) {
                    currency = c;
                    break;
                }
            }
            if (currency != null) {
                TextInputDialog dialog = new TextInputDialog();
                dialog.setTitle("Add Watch:");
                dialog.setContentText("Rate:");
                dialog.setHeaderText(null);
                dialog.setGraphic(null);
                Optional<String> retrievedRate = dialog.showAndWait();
                if (retrievedRate.isPresent()) {
                    currency.setWatchRate(Double.parseDouble(retrievedRate.get()));
                    currency.setWatch(true);
                }
                Launcher.setCurrencyList(currency_list);
                Launcher.refreshPane();
            }
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
