package eu.ase.ro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private float marime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sp = getSharedPreferences("setari", MODE_PRIVATE);

        marime = sp.getFloat("marime", 20);
        seteazaFont();

    }

    public void salveazaShared(View view) {
        String text = ((EditText)findViewById(R.id.editText)).getText().toString();
        SharedPreferences sp = getSharedPreferences("setari", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("text",text);
        editor.putFloat("marime", marime);
        editor.commit();

        FileOutputStream fos = null;
        try {
            fos = openFileOutput("fisier.txt",MODE_PRIVATE);
            fos.write(text.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if(fos!=null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void citesteShared(View view) {
        TextView tv = findViewById(R.id.textView);
        SharedPreferences sp = getSharedPreferences("setari", MODE_PRIVATE);
        tv.setText(sp.getString("text",""));
    }

    private void seteazaFont() {
        Button btn = findViewById(R.id.btn1);
        btn.setTextSize(marime);
        btn = findViewById(R.id.btn2);
        btn.setTextSize(marime);
        btn = findViewById(R.id.btn3);
        btn.setTextSize(marime);
        btn = findViewById(R.id.btn4);
        btn.setTextSize(marime);
    }
    public void cresteDimensiune(View view) {
        marime++;
        seteazaFont();
    }

    public void scadeDimensiune(View view) {
        marime--;
        seteazaFont();
    }
}
