package se233.chapter2.controller;

import javafx.application.Platform;
import javafx.concurrent.Task;
import se233.chapter2.Launcher;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class RefreshTask extends Task<Void> {
    @Override
    protected Void call() {
        while (true) {
            try {
                Thread.sleep(60000);
            } catch (InterruptedException e) {
                System.out.println("RefreshTask interrupted");
            }
            Platform.runLater(() -> {
                try {
                    Launcher.refreshPane();
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            });
            FutureTask<Void> futureTask = new FutureTask<>(new WatchTask());
            Platform.runLater(futureTask);
            try {
                futureTask.get();
            } catch (Exception e) {
                System.out.println("RefreshTask interrupted");
            }
        }
    }
}
