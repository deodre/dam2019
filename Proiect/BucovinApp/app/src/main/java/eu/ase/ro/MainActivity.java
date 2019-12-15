package eu.ase.ro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbarID);
        setSupportActionBar(toolbar);
        NavigationView navigationView = findViewById(R.id.navigationID);
        drawerLayout =findViewById(R.id.drawerID);
        navigationView.setNavigationItemSelectedListener(this);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frameID, new ObiectiveTuristiceFragment()).commit();
        }
        Intent intent = getIntent();
        String username = intent.getStringExtra("credentials");
        View headerView = navigationView.getHeaderView(0);
        TextView credentials = (TextView)headerView.findViewById(R.id.credentialsTV);
        credentials.setText(username);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.obiectiveDrawerMenuID:
                getSupportFragmentManager().beginTransaction().replace(R.id.frameID,new ObiectiveTuristiceFragment()).commit();
                toolbar.setTitle("Obiective Turistice");
                setSupportActionBar(toolbar);
                break;
            case R.id.cazareDraweMenuID:
                getSupportFragmentManager().beginTransaction().replace(R.id.frameID,new CazareFragment()).commit();
                toolbar.setTitle("Cazare");
                setSupportActionBar(toolbar);
                break;
            case R.id.restauranteDrawerMenuID:
                getSupportFragmentManager().beginTransaction().replace(R.id.frameID,new RestauranteFragment()).commit();
                toolbar.setTitle("Restaurante");
                setSupportActionBar(toolbar);
                break;
            case R.id.userDrawerMenuID:
                Intent intent = new Intent(this, UserProfile.class);
                NavigationView navigationView =findViewById(R.id.navigationID);
                View headerView = navigationView.getHeaderView(0);
                TextView credentials = (TextView)headerView.findViewById(R.id.credentialsTV);
                intent.putExtra("profileCredentials", credentials.getText().toString());
                startActivity(intent);
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
