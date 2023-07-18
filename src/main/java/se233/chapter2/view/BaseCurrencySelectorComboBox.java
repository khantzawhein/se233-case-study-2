package se233.chapter2.view;

import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import se233.chapter2.controller.AllEventHandler;
import se233.chapter2.controller.FetchSymbols;
import se233.chapter2.model.Symbol;

public class BaseCurrencySelectorComboBox extends ComboBox<Symbol> {
    public BaseCurrencySelectorComboBox() {
        this.setOnAction(actionEvent -> {
            Symbol baseCurrency = this.getValue();
            if (baseCurrency != null) {
                AllEventHandler.onBaseCurrencyChange(baseCurrency);
            }
        });
        refreshPane();
    }

    public void refreshPane() {
        this.getItems().clear();
        this.getItems().addAll(FetchSymbols.fetch());
    }
}
