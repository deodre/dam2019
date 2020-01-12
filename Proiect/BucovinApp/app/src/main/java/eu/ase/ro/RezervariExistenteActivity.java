package eu.ase.ro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RezervariExistenteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rezervari_existente);

        final ListView rezervarileMeleLV = findViewById(R.id.rezervarileMeleLV);
        final List<Rezervare> rezervarileMele = new ArrayList<>();

        Intent intent = getIntent();
        Cazare cazare = intent.getParcelableExtra("Cazare");

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference().child("rezervare " + cazare.getDenumire());

        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                // String value = dataSnapshot.getValue(String.class);
                Iterable<DataSnapshot> dataSnapshotsList = dataSnapshot.getChildren();
                rezervarileMele.clear();
                for(DataSnapshot d: dataSnapshotsList) {
                    Rezervare rezervare = d.getValue(Rezervare.class);
                    rezervarileMele.add(rezervare);
                }

                RezervareAdapter rezervareAdapter = new RezervareAdapter(getApplicationContext(), R.layout.rezervare_item_layout, rezervarileMele);
                rezervarileMeleLV.setAdapter(rezervareAdapter);
                rezervareAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("FAIL ", "Failed to read value.", error.toException());
            }
        });
    }


}
