package se233.chapter2.model;

import se233.chapter2.Launcher;
import se233.chapter2.controller.FetchData;

import java.util.ArrayList;

public class Currency {
    private String shortCode;
    private CurrencyEntity current;
    private ArrayList<CurrencyEntity> historical;
    private boolean isWatch;
    private Double watchRate;

    public Currency(String shortCode) {
        this.shortCode = shortCode;
        this.isWatch = false;
        this.watchRate = 0.0;
    }

    public String getShortCode() {
        return shortCode;
    }

    public void setShortCode(String shortCode) {
        this.shortCode = shortCode;
    }

    public CurrencyEntity getCurrent() {
        return current;
    }

    public void setCurrent(CurrencyEntity current) {
        this.current = current;
    }

    public ArrayList<CurrencyEntity> getHistorical() {
        return historical;
    }

    public void setHistorical(ArrayList<CurrencyEntity> historical) {
        this.historical = historical;
    }

    public boolean isWatch() {
        return isWatch;
    }

    public void setWatch(boolean watch) {
        isWatch = watch;
    }

    public Double getWatchRate() {
        return watchRate;
    }

    public void setWatchRate(Double watchRate) {
        this.watchRate = watchRate;
    }

    public void refresh() {
        ArrayList<CurrencyEntity> currencyEntities = FetchData.fetch_range(this.shortCode, 30, Launcher.getBaseSymbol().getShortCode());
        this.setHistorical(currencyEntities);
        this.setCurrent(currencyEntities.get(currencyEntities.size() - 1));
    }
}
