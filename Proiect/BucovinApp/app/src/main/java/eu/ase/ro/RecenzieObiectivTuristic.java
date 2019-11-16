package eu.ase.ro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RecenzieObiectivTuristic extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recenzie_obiectiv_turistic);

        Toolbar toolbar = findViewById(R.id.recenzieToolbarID);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        ObiectivTuristic obiectivTuristic = intent.getParcelableExtra("Obiectiv Turistic");
        TextView textView =findViewById(R.id.obiectivTuristicPrimitTV);
        String text = "Cum a fost la " + obiectivTuristic.getDenumire() + "?";
        textView.setText(text);
    }

    public void metodaTrimiteRecenzie(View view) {
        Intent intent = getIntent();
        ObiectivTuristic obiectivTuristic = intent.getParcelableExtra("Obiectiv Turistic");
        String textRecenzie = ((EditText)findViewById(R.id.recenzieET)).getText().toString();
        textRecenzie = textRecenzie.trim();
        if(textRecenzie.equals("") ) {
            Toast.makeText(this,"Completati campul liber cu o recenzie", Toast.LENGTH_SHORT).show();
        } else {
            StringBuilder recenzie = new StringBuilder();
            recenzie.append(textRecenzie).append(" - ").append(obiectivTuristic.toString());
            String trimiteRecenzie = recenzie.toString();
            intent.putExtra("Recenzie", trimiteRecenzie);
            setResult(RESULT_OK, intent);
            finish();
        }
    }

    public void metodaAnulare(View view) {
        setResult(RESULT_CANCELED);
        finish();
    }
}
