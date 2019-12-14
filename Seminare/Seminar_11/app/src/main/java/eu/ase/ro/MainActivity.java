package eu.ase.ro;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ListView cadouriLV = findViewById(R.id.cadouriLV);
        final List<String> cadouri = new ArrayList<>();


        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();

        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                //String value = dataSnapshot.getValue(String.class);
                DataSnapshot ds = dataSnapshot.child("cadouri");
                Iterable<DataSnapshot> cadouriDS = ds.getChildren();
                for(DataSnapshot d: cadouriDS) {

                    Cadou cadou = d.getValue(Cadou.class);
                    Toast.makeText(MainActivity.this, cadou.toString(), Toast.LENGTH_SHORT).show();
                    cadouri.add(cadou.toString());

                }

            }
            ArrayAdapter<String> cadouriAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.activity_list_item, cadouri);
            cadouriLV.setAdapter(cadouriAdapter);

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("FAIL ", "Failed to read value.", error.toException());
            }
        });


    }

    public void metodaInserareFirebase(View view) {

        Cadou cadou = new Cadou(1, 45,true,"Masinuta","Teo");

        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("cadouri");

        DatabaseReference nodCadou = myRef.child("C-"+cadou.getId());
        nodCadou.setValue(cadou);
    }
}
