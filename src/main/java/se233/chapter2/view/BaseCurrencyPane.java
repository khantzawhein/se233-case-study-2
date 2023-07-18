package se233.chapter2.view;

import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import se233.chapter2.Launcher;
import se233.chapter2.model.Symbol;

public class BaseCurrencyPane extends FlowPane {
    Label baseCurrencyLabel;
    ComboBox<Symbol> comboBox;
    public BaseCurrencyPane() {
        this.setPadding(new Insets(10));
        this.setHgap(5);
        this.refreshPane();
    }

    public void refreshPane() {
        baseCurrencyLabel = new Label("Base Currency: ");

        comboBox = new BaseCurrencySelectorComboBox();

        comboBox.setValue(Launcher.getBaseSymbol());

        this.getChildren().addAll(baseCurrencyLabel, comboBox);
    }
}
