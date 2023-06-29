package com.example.bankapp.utility;
import com.example.bankapp.model.BankAppCurrency;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.HashMap;

//import com.google.gson.JsonElement;
//import com.google.gson.JsonObject;
//import com.google.gson.JsonParser;


//https://www.floatrates.com/json-feeds.html

public class ExchangeCurrency {

    public static BigDecimal toTargetRateDefault (BankAppCurrency Base,BankAppCurrency Target) {
        String wantedRate = Base.toString()+Target.toString();

        switch (wantedRate){
            case "EUREUR":
                return new BigDecimal("1");
            case "EURUSD":
                return new BigDecimal("0.9");
            case "EURCAD":
                return new BigDecimal("1.1");
            case "EURGBP":
                return new BigDecimal("1.2");
            case "EURJPY":
                return new BigDecimal("0.1");
            case "EURKRW":
                return new BigDecimal("0.01");
            case "USDEUR":
                return new BigDecimal("1.1");
            case "USDUSD":
                return new BigDecimal("1");
            case "USDCAD":
                return new BigDecimal("1.1");
            case "USDGBP":
                return new BigDecimal("0.9");
            case "USDJPY":
                return new BigDecimal("0.5");
            case "USDKRW":
                return new BigDecimal("0.5");
            case "CADEUR":
                return new BigDecimal("1");
            case "CADUSD":
                return new BigDecimal("1");
            case "CADGBP":
                return new BigDecimal("1");
            case "CADJPY":
                return new BigDecimal("1");
            case "CADKRW":
                return new BigDecimal("1");
            case "GBPEUR":
                return new BigDecimal("1");
            case "GBPUSD":
                return new BigDecimal("1");
            case "GBPCAD":
                return new BigDecimal("1");
            case "GBPGBP":
                return new BigDecimal("1");
            case "GBPJPY":
                return new BigDecimal("1");
            case "GBPKRW":
                return new BigDecimal("1");
            case "JPYEUR":
                return new BigDecimal("1");
            case "JPYUSD":
                return new BigDecimal("1");
            case "JPYCAD":
                return new BigDecimal("1");
            case "JPYGBP":
                return new BigDecimal("1");
            case "JPYKRW":
                return new BigDecimal("1");
            case "KRWEUR":
                return new BigDecimal("1");
            case "KRWUSD":
                return new BigDecimal("1");
            case "KRWCAD":
                return new BigDecimal("1");
            case "KRWGBP":
                return new BigDecimal("1");
            case "KRWJPY":
                return new BigDecimal("1");
            case "KRWKRW":
                return new BigDecimal("1");
            default:
                return new BigDecimal("1");


        }
    }


    public static BigDecimal toTargetRateJSON (BankAppCurrency Base, BankAppCurrency Target) throws IOException {

        BigDecimal rateBigDecimal;
        String targetString = Target.toString().toLowerCase();
        URL url = new URL("http://www.floatrates.com/daily/"+Base+".json");

        //If the website is offline switch to default offline method with predefined rates:
        if (isReachable(url.toString())==false){
            System.out.println("Error while loading URL. Switching to default rates.");
             return toTargetRateDefault(Base,Target);
        }


        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String allLines = reader.lines().reduce("", (a,b)-> a + b);
        ObjectMapper jsonMapper = new ObjectMapper();
        jsonMapper.registerModule(new JavaTimeModule());
        HashMap<String, JsonInner> jsonInnerHashMap = new ObjectMapper().readValue(allLines, new TypeReference<>() {
        });
        System.out.println(jsonInnerHashMap);
        JsonInner inner = jsonInnerHashMap.get(targetString);
        System.out.println(inner);
        String stringTest = jsonInnerHashMap.get(targetString).rate;
//        System.out.println(inner);
        rateBigDecimal = new BigDecimal(inner.rate);


        return rateBigDecimal;


    }

    public static boolean isReachable(String targetUrl) throws IOException
    {
        HttpURLConnection httpUrlConnection = (HttpURLConnection) new URL(
                targetUrl).openConnection();
        httpUrlConnection.setRequestMethod("HEAD");

        try
        {
            int responseCode = httpUrlConnection.getResponseCode();

            return responseCode == HttpURLConnection.HTTP_OK;
        } catch (UnknownHostException noInternetConnection)
        {
            return false;
        }
    }


}
