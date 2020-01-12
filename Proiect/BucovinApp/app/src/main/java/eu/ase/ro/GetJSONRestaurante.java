package dam.ase.ro;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class GetJSONRestaurante extends AsyncTask<String, Void, List<Restaurant>> {
    @Override
    protected List<Restaurant> doInBackground(String... strings) {
        List<Restaurant> result = new ArrayList<>();
        try {
            URL url = new URL(strings[0]);
            HttpURLConnection http = (HttpURLConnection) url.openConnection();

            InputStream inputStream = http.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String linie = null;
            StringBuilder stringBuilder = new StringBuilder();
            while((linie = bufferedReader.readLine()) != null) {
                stringBuilder.append(linie);
            }

            String textJson = stringBuilder.toString();
            JSONObject object = new JSONObject(textJson);
            JSONArray vectorLocalitati = object.getJSONArray("localitate");
            for(int i = 0; i < vectorLocalitati.length(); i++) {
                JSONObject localitateJSON = vectorLocalitati.getJSONObject(i);
                JSONArray vectorRestaurante = localitateJSON.getJSONArray("restaurante");
                for(int j = 0; j < vectorRestaurante.length(); j++) {
                    JSONObject restaurantJSON = vectorRestaurante.getJSONObject(j);
                    Restaurant restaurant = new Restaurant(
                            restaurantJSON.getString("denumire"),
                            restaurantJSON.getString("program"),
                            restaurantJSON.getString("adresa")
                    );
                    result.add(restaurant);
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }
}
