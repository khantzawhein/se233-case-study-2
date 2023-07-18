package se233.chapter2.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import se233.chapter2.controller.AllEventHandler;
import se233.chapter2.controller.draw.DrawCurrencyInfoTask;
import se233.chapter2.controller.draw.DrawGraphTask;
import se233.chapter2.controller.draw.DrawTopAreaTask;
import se233.chapter2.model.Currency;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class CurrencyPane extends BorderPane {
    private Currency currency;

    public CurrencyPane(Currency currency) {
        this.setPadding(new Insets(0));
        this.setPrefSize(640, 300);
        this.setStyle("-fx-border-color: black");
        try {
            this.refreshPane(currency);
        } catch (ExecutionException e) {
            System.out.println("Encountered an execution exception when refreshing the pane");
        } catch (InterruptedException e) {
            System.out.println("Encountered an interrupted exception when refreshing the pane");
        }
    }

    public void refreshPane(Currency currency) throws ExecutionException, InterruptedException {
        this.currency = currency;
        FutureTask<VBox> genInfoPaneTask = new FutureTask<>(new DrawCurrencyInfoTask(currency));
        try (ExecutorService executor = Executors.newSingleThreadExecutor()) {
            executor.execute(genInfoPaneTask);
        }
        VBox currencyInfo = genInfoPaneTask.get();
        FutureTask<VBox> futureTask = new FutureTask<>(new DrawGraphTask(currency));
        try (ExecutorService executor = Executors.newSingleThreadExecutor()) {
            executor.execute(futureTask);
        }
        VBox currencyGraph = futureTask.get();
        FutureTask<HBox> futureTask2 = new FutureTask<>(new DrawTopAreaTask(currency));
        try (ExecutorService executor = Executors.newSingleThreadExecutor()) {
            executor.execute(futureTask2);
        }
        HBox topArea = futureTask2.get();
        this.setTop(topArea);
        this.setLeft(currencyInfo);
        this.setCenter(currencyGraph);
    }


}
