package dam.ase.ro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class RezervareCazare extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rezervare_cazare);

        Toolbar toolbar = findViewById(R.id.rezervareToolbarID);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        Cazare cazare = intent.getParcelableExtra("Cazare");
        TextView textView = findViewById(R.id.detaliiRezervareTV);
        String text = "Faceti o rezervare la :\n" + cazare.getDenumire() +  "\n" + cazare.getTip() + " cu " + cazare.getNrLocuri() + " locuri" + "\n" + cazare.getAdresa();
        textView.setText(text);
    }

    public void metodaTrimiteRezervare(View view) {

        String textNume = ((EditText)findViewById(R.id.numeRezervareET)).getText().toString().trim();
        String textPrenume = ((EditText)findViewById(R.id.prenumeRezervareET)).getText().toString().trim();

        Calendar dataIntrare = Calendar.getInstance();
        DatePicker datePicker = findViewById(R.id.checkInDataID);
        TimePicker timePicker = findViewById(R.id.checkInOraID);
        dataIntrare.set(datePicker.getYear(),datePicker.getMonth(), datePicker.getDayOfMonth(), timePicker.getCurrentHour(), timePicker.getCurrentMinute());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.YYYY' 'HH:mm");
        String dataI = simpleDateFormat.format(dataIntrare.getTime());

        Calendar dataIesire = Calendar.getInstance();
        datePicker = findViewById(R.id.checkOutDataID);
        timePicker = findViewById(R.id.checkOutOraID);
        dataIntrare.set(datePicker.getYear(),datePicker.getMonth(), datePicker.getDayOfMonth(), timePicker.getCurrentHour(), timePicker.getCurrentMinute());
        String dataO = simpleDateFormat.format(dataIesire.getTime());

        if(textNume.equals("") || textPrenume.equals("")) {
            Toast.makeText(this,"Completati spatiile libere", Toast.LENGTH_SHORT).show();
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(textNume).append(" ").append(textPrenume).append("\nCheck-in: ").append(dataI).append("\nCheck-out: ").append(dataO);
            String rezervare = stringBuilder.toString();

            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference("message");

            myRef.setValue("Hello, World!");

            Intent intent = new Intent();
            intent.putExtra("Rezervare", rezervare);
            setResult(RESULT_OK, intent);
            finish();
        }
    }

    public void metodaAnulareRezervare(View view) {
        setResult(RESULT_CANCELED);
        finish();
    }
}
