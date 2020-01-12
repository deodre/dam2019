package eu.ase.ro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class StatisticiPopActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        String username = intent.getStringExtra("username");

        Database database = Room.databaseBuilder(this, Database.class, "Users").allowMainThreadQueries().build();
        int userID = database.getUserDAO().selectSearchUserByUsername(username).getId();

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;

        getWindow().setLayout((int)(width*.9), (int)(height*.7));

        List<Integer> values = new ArrayList<>();
        values.add(database.getRecenzieDAO().selectRecenziiDupaRating(1, userID).size());
        values.add(database.getRecenzieDAO().selectRecenziiDupaRating(2, userID).size());
        values.add(database.getRecenzieDAO().selectRecenziiDupaRating(3, userID).size());
        values.add(database.getRecenzieDAO().selectRecenziiDupaRating(4, userID).size());
        values.add(database.getRecenzieDAO().selectRecenziiDupaRating(5, userID).size());

        PieChart pieChart = new PieChart(this, values);
        setContentView(pieChart);
    }

}
