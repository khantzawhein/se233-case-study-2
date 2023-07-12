package se233.chapter2.view;

import javafx.geometry.Insets;
import javafx.scene.layout.FlowPane;
import se233.chapter2.model.Currency;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class CurrencyParentPane extends FlowPane {
    public CurrencyParentPane(ArrayList<Currency> currencyList) throws InterruptedException, ExecutionException {
        this.setPadding(new Insets(0));
        refreshPane(currencyList);
    }

    public void refreshPane(ArrayList<Currency> currencyList) throws InterruptedException, ExecutionException {
        this.getChildren().clear();
        for (Currency currency : currencyList) {
            CurrencyPane currencyPane = new CurrencyPane(currency);
            this.getChildren().add(currencyPane);
        }
    }
}
