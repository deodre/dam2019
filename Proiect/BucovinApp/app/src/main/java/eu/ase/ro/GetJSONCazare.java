package eu.ase.ro;

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

public class GetJSONCazare extends AsyncTask<String, Void, List<Cazare>> {

    @Override
    protected List<Cazare> doInBackground(String... strings) {
        List<Cazare> result = new ArrayList<>();
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
                JSONArray vectorCazare = localitateJSON.getJSONArray("locatiiCazare");
                for(int j = 0; j < vectorCazare.length(); j++) {
                    JSONObject cazareJSON = vectorCazare.getJSONObject(j);
                    Cazare cazare = new Cazare(
                            cazareJSON.getString("denumire"),
                            cazareJSON.getString("tip"),
                            cazareJSON.getInt("numarLocuri"),
                            cazareJSON.getString("adresa")
                    );
                    result.add(cazare);
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
