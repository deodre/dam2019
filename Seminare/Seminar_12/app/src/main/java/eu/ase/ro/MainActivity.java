package eu.ase.ro;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Desenare desenare = new Desenare(this);
        List<Integer> values = new ArrayList<>();
        values.add(20);
        values.add(50);
        values.add(3);
        values.add(75);
        values.add(10);
        values.add(60);
        PieChart pieChart = new PieChart(this, values);
        setContentView(pieChart);
    }
}
