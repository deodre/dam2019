package eu.ase.ro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;

public class ModificaRecenzie extends AppCompatActivity {

    private Database database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifica_recenzie);
        Intent intent = getIntent();
        Recenzie recenzieModificare = intent.getParcelableExtra("Recenzie");
        EditText editTextRecenzie = findViewById(R.id.recenzieModificareTextET);
        editTextRecenzie.setText(recenzieModificare.getRecenzie());
    }

    public void metodaSalvareModificari(View view) {
        database = Room.databaseBuilder(this, Database.class, "Users").allowMainThreadQueries().build();
        Intent intent = getIntent();
        Recenzie recenzieModificare = intent.getParcelableExtra("Recenzie");
        String recenzieModificata = ((EditText)findViewById(R.id.recenzieModificareTextET)).getText().toString();
        float ratingModifcat = ((RatingBar)findViewById(R.id.ratingBarModificareRecenzie)).getRating();
        database.getRecenzieDAO().updateRecenzie(recenzieModificata, recenzieModificare.getId());
        database.getRecenzieDAO().updateRecenzieRating(ratingModifcat, recenzieModificare.getId());
        setResult(RESULT_OK, intent);
        finish();
    }

    public void metodaCancel(View view) {
        setResult(RESULT_CANCELED);
        finish();
    }
}
