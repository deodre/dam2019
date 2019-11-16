package eu.ase.ro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Main3Activity extends AppCompatActivity {

    private Drona drona;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        drona = getIntent().getParcelableExtra("drona");
        EditText etProducator = findViewById(R.id.producatorET);
        etProducator.setText((drona.getProducator()));
    }

    public void returnDronaCorectata(View view) {
        EditText etProducator = findViewById(R.id.producatorET);
        drona.setProducator(etProducator.getText().toString());
        Intent it = new Intent();
        it.putExtra("drona", drona);
        setResult(RESULT_OK, it);
        finish();
    }
}
