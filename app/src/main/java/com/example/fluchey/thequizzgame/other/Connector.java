package com.example.fluchey.thequizzgame.other;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by fluchey on 2017-04-30.
 */

public class Connector {
    public static String connect (String questionAmount, String category, String difficulty) throws IOException{
        HttpURLConnection connection = null;
        String responseFromApi = "";

        URL url = new URL(makeUrl(questionAmount, category, difficulty));
        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder builder = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null){
            builder.append(line);
        }

        return builder.toString();
    }

    private static String makeUrl(String questionAmount, String category, String difficulty){
        String url = "";
        if (category.equals("8")){
            url = "https://opentdb.com/api.php?amount=" + questionAmount + "&difficulty=" + difficulty
                    + "&encode=url3986";
            Log.d("url", url);
        }else {
            url = "https://opentdb.com/api.php?amount=" + questionAmount + "&category=" + category + "&difficulty=" + difficulty
                    + "&encode=url3986";
            Log.d("url", url);
        }
        return url;
    }
}
