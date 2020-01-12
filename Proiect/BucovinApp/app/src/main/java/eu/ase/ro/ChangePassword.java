package dam.ase.ro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ChangePassword extends AppCompatActivity {

    private Database database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
    }

    public void metodaSalvareModificari(View view) {
        database = Room.databaseBuilder(this, Database.class, "Users").allowMainThreadQueries().build();
        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        String parolaVeche = intent.getStringExtra("parolaveche");
        String parolaNoua = ((EditText)findViewById(R.id.newPasswordET)).getText().toString();
        if(parolaVeche.equals(((EditText)findViewById(R.id.oldPasswordET)).getText().toString())) {
            database.getUserDAO().updateParola(parolaNoua, database.getUserDAO().selectSearchUserByUsername(username).getId());
            setResult(RESULT_OK);
            finish();
        }
        else {
            Toast.makeText(this, "Parola veche nu este corecta", Toast.LENGTH_SHORT).show();
        }

    }

    public void metodaCancel(View view) {
        setResult(RESULT_CANCELED);
        finish();
    }
}
