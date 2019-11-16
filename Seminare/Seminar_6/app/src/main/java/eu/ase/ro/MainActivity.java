package eu.ase.ro;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Calatorie> calatorii;
    private int requestCode = 345;
    private int pozitieAnterioara;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calatorii = new ArrayList<>();
        Calendar c = Calendar.getInstance();
        c.set(2019,10,6);
        calatorii.add(new Calatorie("Roma",3,c.getTime()));

        c.set(2019,10,14);
        calatorii.add(new Calatorie("Paris",2,c.getTime()));

        c.set(2019,11,1);
        calatorii.add(new Calatorie("Alba Iulia",2,c.getTime()));

        ListView listView = findViewById(R.id.listViewID);
        CalatorieAdapter adapter = new CalatorieAdapter(this,R.layout.item_layout, calatorii);
        listView.setAdapter(adapter);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(),Main2Activity.class);
                intent.putExtra("Calatorie", calatorii.get(position));
                startActivityForResult(intent, requestCode);
                pozitieAnterioara = position;
                return true;
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(this.requestCode == requestCode) {
            if (resultCode == RESULT_OK) {
                //Log.e("CE E ASTA AICI", data.getParcelableExtra("Calatorie").toString());
                Calatorie calatorieModificata = data.getParcelableExtra("Calatorie");
                calatorii.get(pozitieAnterioara).setDestinatie(calatorieModificata.getDestinatie());
                calatorii.get(pozitieAnterioara).setNrZile(calatorieModificata.getNrZile());
                calatorii.get(pozitieAnterioara).setDataPlecare(calatorieModificata.getDataPlecare());
                ListView listView = findViewById(R.id.listViewID);
                CalatorieAdapter adapter = new CalatorieAdapter(this, R.layout.item_layout, calatorii);
                listView.setAdapter(adapter);
            } else {
                Toast.makeText(this, "Utilizatorul a renuntat la modificari", Toast.LENGTH_LONG).show();
            }
        }
    }
}
