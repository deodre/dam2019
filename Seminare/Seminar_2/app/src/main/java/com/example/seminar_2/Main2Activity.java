package com.example.seminar_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }

    public void salveazaDrona(View view) {
        EditText et = findViewById(R.id.ETProducator);
        String producator = et.getText().toString();
        et = findViewById(R.id.ETGreutate);
        double greutate = Double.parseDouble(et.getText().toString());
        et = findViewById(R.id.ETElice);
        int nrElice = Integer.parseInt(et.getText().toString());
        CheckBox cb = findViewById(R.id.CBAreCamera);
        boolean areCamera = cb.isChecked();
        RadioGroup rg = findViewById(R.id.RGCuloare);
        RadioButton rb = findViewById(rg.getCheckedRadioButtonId());
        String culoare = rb.getText().toString();

        Drona drona = new Drona(greutate,nrElice,producator,areCamera,culoare);
        //Toast.makeText(this, "Producator: " + producator,Toast.LENGTH_SHORT).show();
        Toast.makeText(this,drona.toString(), Toast.LENGTH_SHORT).show();
    }
}
