package eu.ase.ro;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

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

public class MainActivity extends AppCompatActivity {


    public class MakeRequest extends  AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            String result = null;
            try {
                URL url = new URL(strings[0]);
                HttpURLConnection http = (HttpURLConnection) url.openConnection();
                InputStream is = http.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                String linie = null;
                StringBuilder builder = new StringBuilder();
                while((linie = reader.readLine()) != null) {
                    builder.append(linie);
                }

                String textTotal = builder.toString();

                JSONArray vector = new JSONArray(textTotal);
                JSONObject object = vector.getJSONObject(0);
                result = object.getString("Key");

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
    public class GetJson extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            String result = null;
            try {
                URL url = new URL(strings[0]);
                HttpURLConnection http = (HttpURLConnection) url.openConnection();
                InputStream is = http.getInputStream();

                BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                String linie = null;
                StringBuilder builder = new StringBuilder();
                while((linie = reader.readLine()) != null) {
                    builder.append(linie);
                }

                String textTotal = builder.toString();
                JSONObject object = new JSONObject(textTotal);
                JSONArray vector  = object.getJSONArray("DailyForecasts");
                JSONObject prognozaZi = vector.getJSONObject(0);
                JSONObject temperatura = prognozaZi.getJSONObject("Temperature");
                JSONObject minim = temperatura.getJSONObject("Minimum");
                JSONObject maxim = temperatura.getJSONObject("Maximum");
                result = "Minim: " + minim.getDouble("Value");
                result += " Maxim: " + maxim.getDouble("Value");

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

    public void getPrognozaMethod(View view) {

        MakeRequest makeRequest = new MakeRequest() {
            @Override
            protected void onPostExecute(String s) {
                String key = s;
                GetJson json = new GetJson() {
                    @Override
                    protected void onPostExecute(String s) {
                        Toast.makeText(MainActivity.this, s, Toast.LENGTH_LONG).show();
                    }
                };
                json.execute("http://dataservice.accuweather.com/forecasts/v1/daily/1day/"+key+"?apikey=fmL0txGoamMmRGAcRujtwuXLSpWnNG39&metric=true");
            }
        };
        EditText orasET = findViewById(R.id.orasET);
        String oras = orasET.getText().toString();
        makeRequest.execute("http://dataservice.accuweather.com/locations/v1/cities/search?apikey=fmL0txGoamMmRGAcRujtwuXLSpWnNG39&q="+oras);
//        GetJson json = new GetJson() {
//            @Override
//            protected void onPostExecute(String s) {
//                Toast.makeText(MainActivity.this, s, Toast.LENGTH_LONG).show();
//            }
//        };
//        json.execute("http://dataservice.accuweather.com/forecasts/v1/daily/1day/"+key"+?apikey=fmL0txGoamMmRGAcRujtwuXLSpWnNG39&metric=true");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        GetJson json = new GetJson() {
//          @Override
//          protected void onPostExecute(String s) {
//             Toast.makeText(MainActivity.this, s, Toast.LENGTH_LONG).show();
//         }
//      };
//      json.execute("http://dataservice.accuweather.com/forecasts/v1/daily/1day/275359?apikey=fmL0txGoamMmRGAcRujtwuXLSpWnNG39&metric=true");
    }
}
