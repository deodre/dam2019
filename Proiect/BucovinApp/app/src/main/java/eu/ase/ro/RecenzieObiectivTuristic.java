package dam.ase.ro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class RecenzieObiectivTuristic extends AppCompatActivity {

    private Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recenzie_obiectiv_turistic);

        Toolbar toolbar = findViewById(R.id.recenzieToolbarID);
        setSupportActionBar(toolbar);

        database = Room.databaseBuilder(this, Database.class, "Recenzii").allowMainThreadQueries().build();

        Intent intent = getIntent();
        ObiectivTuristic obiectivTuristic = intent.getParcelableExtra("Obiectiv Turistic");
        TextView textView =findViewById(R.id.obiectivTuristicPrimitTV);
        String text = "Cum a fost la " + obiectivTuristic.getDenumire() + "?";
        textView.setText(text);
    }

    public void metodaTrimiteRecenzie(View view) {
        Intent intent = getIntent();
        ObiectivTuristic obiectivTuristic = intent.getParcelableExtra("Obiectiv Turistic");
        String username = intent.getStringExtra("credentials");
        database = Room.databaseBuilder(this, Database.class, "Users").allowMainThreadQueries().build();
        User user = database.getUserDAO().selectSearchUserByUsername(username);
        String textRecenzie = ((EditText)findViewById(R.id.recenzieET)).getText().toString();
        RatingBar ratingBar = findViewById(R.id.ratingBarRecenzie);
        float rating = ratingBar.getRating();
        textRecenzie = textRecenzie.trim();
        if(textRecenzie.equals("") ) {
            Toast.makeText(this,"Completati campul liber cu o recenzie", Toast.LENGTH_SHORT).show();
        } else {
            Recenzie recenzie = new Recenzie(textRecenzie, rating, user.getId());
            database.getRecenzieDAO().insertRecenzie(recenzie);
            StringBuilder recenzieStringTrimis = new StringBuilder();
            recenzieStringTrimis.append(textRecenzie).append(" - ").append(obiectivTuristic.toString()).append(" - ").append(rating);
            String trimiteRecenzie = recenzieStringTrimis.toString();
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
