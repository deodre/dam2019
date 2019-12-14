package eu.ase.ro;

import android.database.AbstractWindowedCursor;
import android.os.AsyncTask;
import android.util.JsonReader;
import android.util.Log;

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

public class GetJSONObiectiveTuristice extends AsyncTask<String, Void, List<ObiectivTuristic>> {
    @Override
    protected List<ObiectivTuristic> doInBackground(String... strings) {
        List<ObiectivTuristic> result = new ArrayList<>();
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
                JSONArray vectorObiective = localitateJSON.getJSONArray("obiectiveTuristice");
                for(int j = 0; j < vectorObiective.length(); j++) {
                    JSONObject obiectivTuristicJSON = vectorObiective.getJSONObject(j);
                    ObiectivTuristic obiectivTuristic = new ObiectivTuristic(
                            obiectivTuristicJSON.getString("denumire"),
                            obiectivTuristicJSON.getString("tip"),
                            obiectivTuristicJSON.getInt("anulConstructiei")
                    );
                    result.add(obiectivTuristic);
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
