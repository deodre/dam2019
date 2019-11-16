package eu.ase.ro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        //preluam textul primit si il punem in textview
        ((EditText)findViewById(R.id.textPrimitID)).setText(getIntent().getStringExtra("text"));
    }

    public void metodaOK(View view) {
        //preluam datele din activitate
        String text = ((EditText)findViewById(R.id.textPrimitID)).getText().toString();
        DatePicker dp = findViewById(R.id.dataID);
        //calendar e clasa singleton pentru ca nu are constructor ci are metoda asta cu getInstance
        //Calendar din java.util
        Calendar c = Calendar.getInstance();
        c.set(dp.getYear(),dp.getMonth(),dp.getDayOfMonth());
        SimpleDateFormat s = new SimpleDateFormat("dd.MM.YYYY");
        String data = s.format(c.getTime());

        //luam si rating bar
        float nota = ((RatingBar)findViewById(R.id.notaID)).getRating();

        //ne cream un string cu toate datele
        StringBuilder sb = new StringBuilder();
        sb.append(text).append(" - ").append(data).append(" - ").append(nota);
        String textFinal = sb.toString();

        //returnam textul final in activitatea intiala si o inchidem pe asta
        Intent it = new Intent();
        it.putExtra("textFinal",textFinal);
        setResult(RESULT_OK, it);
        finish();
    }

    public void metodaCancel(View view) {
        //dam cancel si inchidem activitatea
        setResult(RESULT_CANCELED);
        finish();
    }
}