package eu.ase.ro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ShareActionProvider;
import android.widget.Toast;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        SharedPreferences sharedPreferences = getSharedPreferences("credentials", MODE_PRIVATE);
        String username = sharedPreferences.getString("username", null);
        String password = sharedPreferences.getString("password", null);
        if(username != null && password != null) {
            Intent intent = new Intent(this, MainActivity.class);
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
        else {
            if(checkBox.isChecked()) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("username", username);
                editor.putString("password", password);
                editor.apply();
            }
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }
}
