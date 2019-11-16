package eu.ase.ro;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private final int codModificaDrona = 303;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn = findViewById(R.id.btnModificaDrona);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getApplicationContext(),MainActivity.class);
                Drona drona = new Drona(2,"yterter",4,true,"Alb");
                it.putExtra("drona", drona);
                startActivityForResult(it, codModificaDrona);
            }
        });
    }

    public void metodaDeshidereActivitate(View view) {
        EditText et = findViewById(R.id.editTextID);
        String textIntrodus = et.getText().toString();

        Drona drona = new Drona(2,"Sony",3,true,"Negru");
        Intent it = new Intent(this,Main2Activity.class);
        it.putExtra("text",textIntrodus);
        it.putExtra("drona",drona);
        startActivity(it);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == codModificaDrona) {
            if(resultCode == RESULT_OK) {
                Toast.makeText(this,((Drona)data.getParcelableExtra("drona")).toString(),Toast.LENGTH_LONG).show();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
