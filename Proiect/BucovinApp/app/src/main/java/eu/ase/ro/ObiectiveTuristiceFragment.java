package eu.ase.ro;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;

public class ObiectiveTuristiceFragment extends Fragment {

    private List<ObiectivTuristic> obiective;
    private int requestCode = 345;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.obiective_turistice_fragment,container,false);
        obiective = new ArrayList<>();
        NavigationView navigationView = (NavigationView) getActivity().findViewById(R.id.navigationID);
        View headerView = navigationView.getHeaderView(0);
        TextView credentials = (TextView)headerView.findViewById(R.id.credentialsTV);
        String username = credentials.getText().toString();

        GetJSONObiectiveTuristice getJSONObiectiveTuristice = new GetJSONObiectiveTuristice() {
            @Override
            protected void onPostExecute(List<ObiectivTuristic> obiectivTuristics) {
                for(ObiectivTuristic obiectiv : obiectivTuristics) {
                    obiective.add(obiectiv);
                    ListView listView = (ListView)view.findViewById(R.id.obiectiveTuristiceListViewID);
                    ObiectivTuristicAdapter obiectivTuristicAdapter = new ObiectivTuristicAdapter(getActivity(),R.layout.obiectiv_turistic_item_layout, obiective);
                    listView.setAdapter(obiectivTuristicAdapter);

                    listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                        @Override
                        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                            Intent intent = new Intent(getActivity(), RecenzieObiectivTuristic.class);
                            intent.putExtra("Obiectiv Turistic", obiective.get(position));
                            intent.putExtra("credentials", username);
                            startActivityForResult(intent, requestCode);
                            return  true;
                        }
                    });
                }
            }
        };
        getJSONObiectiveTuristice.execute("https://api.myjson.com/bins/y2a3k");
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(this.requestCode == requestCode) {
            if(resultCode == RESULT_OK) {
                Toast.makeText(getActivity(),"Recenzia primita!\n" + data.getStringExtra("Recenzie"),Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getActivity(),"Recenzia anulata", Toast.LENGTH_LONG).show();
            }
        }
    }
}
