package se233.chapter2.controller.draw;

import javafx.scene.layout.HBox;
import se233.chapter2.model.Currency;

import java.util.concurrent.Callable;

public class DrawTopAreaTask implements Callable<HBox> {

    private final Currency currency;

    public DrawTopAreaTask(Currency currency) {
        this.currency = currency;
    }
    @Override
    public HBox call() {
        System.out.println(Thread.currentThread().getName());
        DrawTopPane drawTopPane = new DrawTopPane(this.currency);
        return drawTopPane.genTopArea();
    }
}
