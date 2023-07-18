package se233.chapter2.controller.draw;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import se233.chapter2.controller.AllEventHandler;
import se233.chapter2.model.Currency;

public class DrawTopPane {
    private Button watch;
    private Button unwatch;
    private Button delete;
    public DrawTopPane(Currency currency) {
        this.watch = new Button("Watch");
        this.watch.setOnAction(actionEvent -> AllEventHandler.onWatch(currency.getShortCode()));
        this.unwatch = new Button("Unwatch");
        this.unwatch.setOnAction(actionEvent -> AllEventHandler.onUnwatch(currency));
        this.delete = new Button("Delete");
        this.delete.setOnAction(actionEvent -> AllEventHandler.onDelete(currency.getShortCode()));
    }

    public HBox genTopArea() {
        HBox topArea = new HBox(10);
        topArea.setPadding(new Insets(5));
        topArea.getChildren().addAll(watch, unwatch, delete);
        topArea.setAlignment(Pos.CENTER_RIGHT);
        return topArea;
    }
}
