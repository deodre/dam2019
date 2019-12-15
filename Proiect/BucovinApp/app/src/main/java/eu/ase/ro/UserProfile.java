package eu.ase.ro;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.room.Room;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class UserProfile extends AppCompatActivity {

    Toolbar toolbar;
    private Database database;
    private int requestCode = 467;
    private int requestCode1 = 345;
    private int requestCode2 = 357;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        toolbar = findViewById(R.id.userProfileToolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Profile");
        Intent intent = getIntent();
        String username = intent.getStringExtra("profileCredentials");
        TextView infoUtilizatorTV = findViewById(R.id.iformatiiUtilizatorTV);
        database = Room.databaseBuilder(this, Database.class, "Users").allowMainThreadQueries().build();
        infoUtilizatorTV.setText(database.getUserDAO().selectSearchUserByUsername(username).toString());
    }

    public void metodaLogOut(View view) {
        SharedPreferences sharedPreferences = getSharedPreferences("credentials", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("username", null);
        editor.putString("password", null);
        editor.apply();
        Intent intent = new Intent(this, StartActivity.class);
        startActivity(intent);
    }

    public void metodaGetRecenzii(View view) {
        database = Room.databaseBuilder(this, Database.class, "Users").allowMainThreadQueries().build();
        Intent intent = getIntent();
        String username = intent.getStringExtra("profileCredentials");

        List<Recenzie> recenzii = database.getRecenzieDAO().selectToateRecenziile(database.getUserDAO().selectSearchUserByUsername(username).getId());
        ListView recenziiLV = findViewById(R.id.recenziiLV);

        ArrayAdapter<Recenzie> recenziiAdapter = new ArrayAdapter<Recenzie>(this,android.R.layout.simple_list_item_1,recenzii);
        recenziiLV.setAdapter(recenziiAdapter);

        recenziiLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent1 = new Intent(getApplicationContext(), ModificaRecenzie.class);
                intent1.putExtra("Recenzie", recenzii.get(position));
                startActivityForResult(intent1, requestCode1);
            }
        });

        recenziiLV.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                database.getRecenzieDAO().deleteRecenzie(recenzii.get(position).getId());
                Toast.makeText(UserProfile.this, "Deleted, please refresh!", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }

    public void metodaReceneziiPozitive(View view) {
        database = Room.databaseBuilder(this, Database.class, "Users").allowMainThreadQueries().build();
        Intent intent = getIntent();
        String username = intent.getStringExtra("profileCredentials");

        List<Recenzie> recenzii = database.getRecenzieDAO().selectRecenziiPozitive(database.getUserDAO().selectSearchUserByUsername(username).getId());
        ListView recenziiLV = findViewById(R.id.recenziiLV);

        ArrayAdapter<Recenzie> recenziiAdapter = new ArrayAdapter<Recenzie>(this,android.R.layout.simple_list_item_1,recenzii);
        recenziiLV.setAdapter(recenziiAdapter);

        recenziiLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent1 = new Intent(getApplicationContext(), ModificaRecenzie.class);
                intent1.putExtra("Recenzie", recenzii.get(position));
                startActivityForResult(intent1, requestCode2);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.user_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id =item.getItemId();
        if(id == R.id.changePasswordOptionID) {
            Intent intent = getIntent();
            String username = intent.getStringExtra("profileCredentials");
            Intent intent2 = new Intent(this, ChangePassword.class);
            intent2.putExtra("username", username);
            intent2.putExtra("parolaveche" , database.getUserDAO().selectSearchUserByUsername(username).getParola());
            startActivityForResult(intent2, requestCode);
        }
        else if(id == R.id.deleteAccountID){
            database = Room.databaseBuilder(this, Database.class, "Users").allowMainThreadQueries().build();
            Intent intent = getIntent();
            String username = intent.getStringExtra("profileCredentials");
            database.getUserDAO().deleteUser(database.getUserDAO().selectSearchUserByUsername(username).getId());
            SharedPreferences sharedPreferences = getSharedPreferences("credentials", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("username", null);
            editor.putString("password", null);
            editor.apply();
            Intent intent2 = new Intent(this, StartActivity.class);
            startActivity(intent2);
        }
        else {
            Intent intent = getIntent();
            String username = intent.getStringExtra("profileCredentials");
            List<Recenzie> recenzii = database.getRecenzieDAO().selectToateRecenziile(database.getUserDAO().selectSearchUserByUsername(username).getId());
            List<Recenzie> recenziiPozitive = database.getRecenzieDAO().selectRecenziiPozitive(database.getUserDAO().selectSearchUserByUsername(username).getId());
            String space = "\n";
            FileOutputStream fos = null;
            try {
                fos = openFileOutput("Toate recenziile.txt",MODE_PRIVATE);
                for(int i = 0; i < recenzii.size(); i++) {
                    fos.write(recenzii.get(i).toString().getBytes());
                    fos.write(space.getBytes());
                }
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
            try {
                fos = openFileOutput("Recenziile Pozitive.txt",MODE_PRIVATE);
                for(int i = 0; i < recenziiPozitive.size(); i++) {
                    fos.write(recenziiPozitive.get(i).toString().getBytes());
                    fos.write(space.getBytes());
                }
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
            Toast.makeText(this, "Fisiere Salvate", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(this.requestCode == requestCode) {
            if(resultCode == RESULT_OK) {
                finish();
                startActivity(getIntent());
            }
        }
        else if(this.requestCode == requestCode1){
            if(resultCode == RESULT_OK) {
                Intent intent = getIntent();
                String username = intent.getStringExtra("profileCredentials");
                List<Recenzie> recenzii = database.getRecenzieDAO().selectToateRecenziile(database.getUserDAO().selectSearchUserByUsername(username).getId());
                ListView recenziiLV = findViewById(R.id.recenziiLV);

                ArrayAdapter<Recenzie> recenziiAdapter = new ArrayAdapter<Recenzie>(this,android.R.layout.simple_list_item_1,recenzii);
                recenziiLV.setAdapter(recenziiAdapter);
                Toast.makeText(UserProfile.this, "Updated please refresh!", Toast.LENGTH_SHORT).show();
            }
        }
        else if(this.requestCode == requestCode2) {
            Intent intent = getIntent();
            String username = intent.getStringExtra("profileCredentials");
            List<Recenzie> recenzii = database.getRecenzieDAO().selectRecenziiPozitive(database.getUserDAO().selectSearchUserByUsername(username).getId());
            ListView recenziiLV = findViewById(R.id.recenziiLV);

            ArrayAdapter<Recenzie> recenziiAdapter = new ArrayAdapter<Recenzie>(this,android.R.layout.simple_list_item_1,recenzii);
            recenziiLV.setAdapter(recenziiAdapter);
            Toast.makeText(UserProfile.this, "Updated please refresh!", Toast.LENGTH_SHORT).show();

        }
    }
}
