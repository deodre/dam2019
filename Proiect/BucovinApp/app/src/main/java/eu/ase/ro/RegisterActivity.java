package eu.ase.ro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    private Database database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        database = Room.databaseBuilder(this, Database.class, "Users").allowMainThreadQueries().build();
    }

    public void metodaSaveRegister(View view) {
        String nume = ((EditText)findViewById(R.id.numeRegisterET)).getText().toString();
        String prenume = ((EditText)findViewById(R.id.prenumeRegisterET)).getText().toString();
        String username = ((EditText)findViewById(R.id.usernameRegisterET)).getText().toString();
        String email = ((EditText)findViewById(R.id.emailRegisterET)).getText().toString();
        String parola = ((EditText)findViewById(R.id.passwordRegisterET)).getText().toString();
        User user = new User(username, nume, prenume, email, parola);

        database.getUserDAO().insertUser(user);

        Intent intent = getIntent();
        setResult(RESULT_OK, intent);
        finish();
    }
}
