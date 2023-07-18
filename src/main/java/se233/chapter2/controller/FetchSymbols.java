package se233.chapter2.controller;

import org.apache.commons.io.Charsets;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
import se233.chapter2.model.Symbol;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

public class FetchSymbols {
    public static ArrayList<Symbol> fetch() {
        String url = "https://api.exchangerate.host/symbols";
        ArrayList<Symbol> symbolList = new ArrayList<>();
        String retrievedJson = null;

        try {
            retrievedJson = IOUtils.toString(URI.create(url).toURL(), Charset.defaultCharset());
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONObject jsonObject = new JSONObject(retrievedJson).getJSONObject("symbols");
        Iterator<String> symbols = jsonObject.keys();

        while (symbols.hasNext()) {
            String key = symbols.next();
            JSONObject symbol = jsonObject.getJSONObject(key);
            symbolList.add(new Symbol(symbol.getString("code"), symbol.getString("description")));
        }

        symbolList.sort(Comparator.comparing(Symbol::getShortCode));

        return symbolList;
    }
}
