package se233.chapter2.controller;

import javafx.application.Platform;
import se233.chapter2.Launcher;
import se233.chapter2.model.Currency;

import java.util.concurrent.Callable;

public class RefreshDataTask implements Runnable {

    public void refreshData() {
        for (Currency currency : Launcher.getCurrencyList()) {
            currency.refresh();
        }
    }

    @Override
    public void run() {
        this.refreshData();
        Platform.runLater(() -> {
            try {
                Launcher.getTopPane().refreshPane();
                Launcher.getCurrencyParentPane().refreshPane(Launcher.getCurrencyList());
                Launcher.getPrimaryStage().sizeToScene();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
