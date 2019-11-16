package eu.ase.ro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Intent it = getIntent();
        Drona drona = it.getParcelableExtra("drona");
        Bundle extras = it.getExtras();
        String textPrimit = extras.getString("text", "");
        Toast.makeText(this, drona.toString(), Toast.LENGTH_LONG).show();
    }
}
