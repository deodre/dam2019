package eu.ase.ro;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;

public class ObiectiveTuristiceFragment extends Fragment {

    private List<ObiectivTuristic> obiective;
    private int requestCode = 345;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.obiective_turistice_fragment,container,false);
        obiective = new ArrayList<>();
        obiective.add(new ObiectivTuristic("Manastirea Voronet","Complex Monahal Medieval", 1488));
        obiective.add(new ObiectivTuristic("Cetatea De Scaun ","Cetate Medievala", 1391));
        obiective.add(new ObiectivTuristic("Manastirea Putna","Complex Monahal Medieval", 1469));

        ListView listView = (ListView)view.findViewById(R.id.obiectiveTuristiceListViewID);
        ObiectivTuristicAdapter obiectivTuristicAdapter = new ObiectivTuristicAdapter(getActivity(),R.layout.obiectiv_turistic_item_layout, obiective);
        listView.setAdapter(obiectivTuristicAdapter);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(),RecenzieObiectivTuristic.class);
                intent.putExtra("Obiectiv Turistic", obiective.get(position));
                startActivityForResult(intent, requestCode);
                return  true;
            }
        });

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
