package eu.ase.ro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private CadouDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        database = Room.databaseBuilder(this,CadouDatabase.class, "Wishlist").allowMainThreadQueries().build();
    }

    public void insertDB(View view) {
        int id = Integer.parseInt(((EditText)findViewById(R.id.idET)).getText().toString());
        String denumire = ((EditText)findViewById(R.id.denumireET)).getText().toString();
        float pret = Float.parseFloat(((EditText)findViewById(R.id.pretET)).getText().toString());
        boolean impachetat = ((CheckBox)findViewById(R.id.impachetatCB)).isChecked();

        Cadou cadou = new Cadou(id, denumire,pret, impachetat);

        database.getCadouDAO().insertCadou(cadou);
    }

    public void getCadou(View view) {
        Toast.makeText(this,database.getCadouDAO().selectPrimulCadou().toString(), Toast.LENGTH_LONG).show();
    }

    public void searchCadou(View view) {

        int id = Integer.parseInt(((EditText)findViewById(R.id.cautaCadouET)).getText().toString());
        Toast.makeText(this, database.getCadouDAO().selectCadouCautat(id).toString(), Toast.LENGTH_LONG).show();
    }
}
