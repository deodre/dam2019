package eu.ase.ro;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String link = "https://www.bnr.ro/nbrfxrates.xml";
        GetXMLRates getXMLRates = new GetXMLRates() {
            @Override
            protected void onPostExecute(List<Rate> rates) {


                Toast.makeText(MainActivity.this, rates.get(0).toString(), Toast.LENGTH_SHORT).show();

                Spinner mySpinner = findViewById(R.id.spinnerID);
                List<String> currencies = new ArrayList<>();
                for(Rate rate : rates){
                    currencies.add(rate.getCurrency());
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,currencies);
                mySpinner.setAdapter(adapter);
                super.onPostExecute(rates);
            }
        };
        getXMLRates.execute(link);

    }
}
