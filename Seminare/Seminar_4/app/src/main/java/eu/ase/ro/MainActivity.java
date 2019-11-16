package eu.ase.ro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbarID);
        NavigationView navigationView = findViewById(R.id.navigationID);
        drawerLayout=findViewById(R.id.drawerID);
        navigationView.setNavigationItemSelectedListener(this);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frameID, new HomeFragment()).commit();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.homeID:
                getSupportFragmentManager().beginTransaction().replace(R.id.frameID,new HomeFragment()).commit();
                break;
            case R.id.settingsID:
                getSupportFragmentManager().beginTransaction().replace(R.id.frameID,new SettingsFragment()).commit();
                break;
            case R.id.aboutID:
                getSupportFragmentManager().beginTransaction().replace(R.id.frameID,new AboutFragment()).commit();
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }
}
