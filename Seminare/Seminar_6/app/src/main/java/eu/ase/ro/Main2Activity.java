package eu.ase.ro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Intent intent = getIntent();
        Calatorie calatorie = intent.getParcelableExtra("Calatorie");
        //Log.w("AICI CRAPA", " "+calatorie.getDestinatie());
        EditText editText = findViewById(R.id.destinatieET);
        editText.setText(calatorie.getDestinatie());
        editText = findViewById(R.id.durataET);
        editText.setText(String.valueOf(calatorie.getNrZile()));
    }

    public void metodaOK(View view) {
        Intent intent = getIntent();
        Calatorie calatorie = intent.getParcelableExtra("Calatorie");
        calatorie.setDestinatie(((EditText)findViewById(R.id.destinatieET)).getText().toString());
        calatorie.setNrZile(Integer.parseInt(((EditText)findViewById(R.id.durataET)).getText().toString()));
        DatePicker datePicker = findViewById(R.id.dataPlecareDP);
        Calendar c = Calendar.getInstance();
        c.set(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth());
        calatorie.setDataPlecare(c.getTime());
        intent.putExtra("Calatorie", calatorie);
        setResult(RESULT_OK, intent);
        finish();
    }

    public void metodaCancel(View view) {
        setResult(RESULT_CANCELED);
        finish();
    }
}
