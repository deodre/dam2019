package eu.ase.ro;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> texte;
    private int requestCode = 345;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        texte = new ArrayList<>();

        ListView listView = findViewById(R.id.listaLV);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), Main2Activity.class);
                intent.putExtra("text", texte.get(position));
                startActivityForResult(intent, requestCode);
            }
        });
    }

    public void adaugareText(View view) {
        EditText editText = findViewById(R.id.textID);
        String text = editText.getText().toString();
        texte.add(text);
        editText.setText("");

        ListView listView = findViewById(R.id.listaLV);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,texte);
        listView.setAdapter(adapter);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(this.requestCode == requestCode) {
            if(resultCode == RESULT_OK) {
                Toast.makeText(this,data.getStringExtra("textFinal"),Toast.LENGTH_LONG).show();
            }
            else {
                Toast.makeText(this,getString(R.string.toast_canceled),Toast.LENGTH_LONG).show();
            }
        }
    }
}
