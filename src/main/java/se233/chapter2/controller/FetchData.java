package se233.chapter2.controller;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
import se233.chapter2.model.CurrencyEntity;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

public class FetchData {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static ArrayList<CurrencyEntity> fetch_range(String src, int N, String base) {

        String dateEnd = LocalDate.now().format(formatter);
        String dateStart = LocalDate.now().minusDays(N).format(formatter);
        String url_str = String.format("https://api.exchangerate.host/timeseries?start_date=%s&end_date=%s&base=%s&symbols=%s", dateStart, dateEnd, base, src);
        ArrayList<CurrencyEntity> histList = new ArrayList<>();
        String retrievedJson = null;
        try {
            retrievedJson = IOUtils.toString(URI.create(url_str).toURL(), Charset.defaultCharset());
        } catch (MalformedURLException e) {
            System.out.println("Encountered a Malformed Url Exception");
        } catch (IOException e) {
            System.out.println("Encountered an IO Exception");
        }
        JSONObject jsonOBJ = new JSONObject(retrievedJson).getJSONObject("rates");
        Iterator<String> keysToCopyIterator = jsonOBJ.keys();
        while (keysToCopyIterator.hasNext()) {
            String key = keysToCopyIterator.next();
            if (!jsonOBJ.getJSONObject(key).isEmpty()) {
                Double rate = 1.0 / Double.parseDouble(jsonOBJ.getJSONObject(key).get(src).toString());
                histList.add(new CurrencyEntity(rate, key));
            }
        }
        histList.sort(Comparator.comparing(CurrencyEntity::getTimestamp));

        return histList;
    }
}
