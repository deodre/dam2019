package com.example.seminar_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }

    @Override
    protected void onPause(){
        super.onPause();
        Log.i("Activitate", "A fost apelata metoda onPause");
    }

    @Override
    protected void onResume(){
        super.onResume();
        Log.e("Activitate", "A fost apelata metoda onResume");
        Toast.makeText(this, "Bine ati revenit!", Toast.LENGTH_LONG).show();
    }

}
