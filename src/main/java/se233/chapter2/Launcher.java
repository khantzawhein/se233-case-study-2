package se233.chapter2;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import se233.chapter2.controller.FetchSymbols;
import se233.chapter2.controller.Initialize;
import se233.chapter2.controller.RefreshDataTask;
import se233.chapter2.controller.RefreshTask;
import se233.chapter2.model.Currency;
import se233.chapter2.model.Symbol;
import se233.chapter2.view.BaseCurrencyPane;
import se233.chapter2.view.CurrencyParentPane;
import se233.chapter2.view.TopPane;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Launcher extends Application {
    private static Stage primaryStage;
    private static Scene mainScene;
    private static FlowPane mainPane;
    private static TopPane topPane;
    private static CurrencyParentPane currencyParentPane;
    private static ArrayList<Currency> currencyList;
    private static Symbol baseSymbol;

    private static ArrayList<Symbol> symbolList;

    private static BaseCurrencyPane baseCurrencyPane;

    @Override
    public void start(Stage primaryStage) throws ExecutionException, InterruptedException {
        Launcher.primaryStage = primaryStage;
        Launcher.primaryStage.setTitle("Currency Watcher");
        Launcher.primaryStage.setResizable(false);
        Launcher.symbolList = FetchSymbols.fetch();
        Launcher.baseSymbol = new Symbol("THB", "Thai Baht");
        Launcher.currencyList = Initialize.initialize_app();
        this.initMainPane();
        Launcher.mainScene = new Scene(mainPane);
        Launcher.primaryStage.setScene(mainScene);
        Launcher.primaryStage.show();
        RefreshTask r = new RefreshTask();
        Thread t = new Thread(r);
        t.setDaemon(true);
        t.start();
    }

    public static void refreshPane() throws InterruptedException, ExecutionException {
        try  {
            Thread t = new Thread(new RefreshDataTask());
            t.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initMainPane() throws ExecutionException, InterruptedException {
        mainPane = new FlowPane();
        topPane = new TopPane();
        baseCurrencyPane = new BaseCurrencyPane();
        currencyParentPane = new CurrencyParentPane(currencyList);
        mainPane.getChildren().addAll(topPane, baseCurrencyPane, currencyParentPane);
    }



    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void setPrimaryStage(Stage primaryStage) {
        Launcher.primaryStage = primaryStage;
    }

    public static Scene getMainScene() {
        return mainScene;
    }

    public static void setMainScene(Scene mainScene) {
        Launcher.mainScene = mainScene;
    }

    public static FlowPane getMainPane() {
        return mainPane;
    }

    public static void setMainPane(FlowPane mainPane) {
        Launcher.mainPane = mainPane;
    }

    public static TopPane getTopPane() {
        return topPane;
    }

    public static void setTopPane(TopPane topPane) {
        Launcher.topPane = topPane;
    }

    public static CurrencyParentPane getCurrencyParentPane() {
        return currencyParentPane;
    }

    public static void setCurrencyParentPane(CurrencyParentPane currencyParentPane) {
        Launcher.currencyParentPane = currencyParentPane;
    }

    public static ArrayList<Currency> getCurrencyList() {
        return currencyList;
    }

    public static void setCurrencyList(ArrayList<Currency> currencyList) {
        Launcher.currencyList = currencyList;
    }

    public static Symbol getBaseSymbol() {
        return baseSymbol;
    }

    public static void setBaseSymbol(Symbol baseSymbol) {
        Launcher.baseSymbol = baseSymbol;
    }

    public static void setSymbolList(ArrayList<Symbol> symbolList) {
        Launcher.symbolList = symbolList;
    }

    public static ArrayList<Symbol> getSymbolList() {
        return symbolList;
    }
}
