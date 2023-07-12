package se233.chapter2.view;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import se233.chapter2.controller.AllEventHandler;

import java.time.LocalDateTime;

public class TopPane extends FlowPane {
    private Button refresh;
    private Button add;
    private Label update;

    public TopPane() {
        this.setPadding(new Insets(10));
        this.setHgap(10);
        this.setPrefSize(640, 20);

        add = new Button("Add");
        add.setOnAction(actionEvent -> AllEventHandler.onAdd());
        refresh = new Button("Refresh");
        refresh.setOnAction(actionEvent -> AllEventHandler.onRefresh());
        update = new Label();
        refreshPane();
        this.getChildren().addAll(refresh, add, update);
    }

    public void refreshPane() {
        update.setText(String.format("Last updated: %s", LocalDateTime.now().toString()));
    }
}
