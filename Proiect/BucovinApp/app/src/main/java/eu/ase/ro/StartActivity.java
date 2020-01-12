package eu.ase.ro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class StartActivity extends AppCompatActivity {

    private Database database;
    private int requestCode = 203;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        database = Room.databaseBuilder(this, Database.class, "Users").allowMainThreadQueries().build();
        SharedPreferences sharedPreferences = getSharedPreferences("credentials", MODE_PRIVATE);
        String username = sharedPreferences.getString("username", null);
        String password = sharedPreferences.getString("password", null);
        if(username != null && password != null) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("credentials", username);
            startActivity(intent);
        }
    }

    public void metodaLogIn(View view) {
        CheckBox checkBox = findViewById(R.id.keepCredentialsCB);
        SharedPreferences sharedPreferences = getSharedPreferences("credentials", MODE_PRIVATE);
        String username = ((EditText)findViewById(R.id.usernameID)).getText().toString();
        String password = ((EditText)findViewById(R.id.passwordID)).getText().toString();
        if(username.equals("") || password.equals("")) {
            Toast.makeText(this, "Please provide credentials", Toast.LENGTH_SHORT).show();
        }
        if(database.getUserDAO().selectSearchUserByUsername(username) != null &&
                database.getUserDAO().selectSearchUserByPassword(password) != null) {


            if(checkBox.isChecked()) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("username", username);
                editor.putString("password", password);
                editor.apply();
            }
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("credentials", username);
            startActivity(intent);
        }
        else {
            Toast.makeText(this, "Inexistent User", Toast.LENGTH_SHORT).show();
        }
    }

    public void metodaRegister(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivityForResult(intent, requestCode);
    }
}
