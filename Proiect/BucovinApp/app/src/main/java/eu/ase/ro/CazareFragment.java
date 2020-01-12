package dam.ase.ro;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static android.app.Activity.RESULT_OK;

public class CazareFragment extends Fragment {


    private List<Cazare> locatiiCazare;
    private List<Cazare> hoteluri;
    private List<Cazare> pensiuni;
    private int requestCode = 234;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.cazare_fragment,container,false);
        locatiiCazare = new ArrayList<>();

        GetJSONCazare getJSONCazare = new GetJSONCazare() {
            @Override
            protected void onPostExecute(List<Cazare> cazares) {
                for(Cazare cazare : cazares) {
                    locatiiCazare.add(cazare);

                    final ListView listView = view.findViewById(R.id.cazareListViewID);
                    CazareAdapter cazareAdapter = new CazareAdapter(getActivity(),R.layout.cazare_item_layout, locatiiCazare);
                    listView.setAdapter(cazareAdapter);

                    Spinner spinner = view.findViewById(R.id.spinnerCazareID);
                    spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            if(position == 0) {
                                CazareAdapter cazareAdapter = new CazareAdapter(getActivity(),R.layout.cazare_item_layout, locatiiCazare);
                                listView.setAdapter(cazareAdapter);
                                listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                                    @Override
                                    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                                        Intent intent = new Intent(getActivity(),RezervareCazare.class);
                                        intent.putExtra("Cazare", locatiiCazare.get(position));
                                        startActivityForResult(intent, requestCode);
                                        return true;
                                    }
                                });

                            } else if(position == 1) {
                                List<Cazare> hoteluri = locatiiCazare.stream().filter(cazare -> cazare.getTip().contains("Hotel")).collect(Collectors.toList());
                                CazareAdapter hoteluriAdapter = new CazareAdapter(getActivity(),R.layout.cazare_item_layout, hoteluri);
                                listView.setAdapter(hoteluriAdapter);
                                listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                                    @Override
                                    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                                        Intent intent = new Intent(getActivity(),RezervareCazare.class);
                                        intent.putExtra("Cazare", hoteluri.get(position));
                                        startActivityForResult(intent, requestCode);
                                        return true;
                                    }
                                });
                            } else {
                                List<Cazare> pensiuni = locatiiCazare.stream().filter(cazare -> cazare.getTip().contains("Pensiune")).collect(Collectors.toList());
                                CazareAdapter pensiuniAdapter = new CazareAdapter(getActivity(),R.layout.cazare_item_layout, pensiuni);
                                listView.setAdapter(pensiuniAdapter);
                                listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                                    @Override
                                    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                                        Intent intent = new Intent(getActivity(),RezervareCazare.class);
                                        intent.putExtra("Cazare", pensiuni.get(position));
                                        startActivityForResult(intent, requestCode);
                                        return true;
                                    }
                                });
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }
            }
        };
        getJSONCazare.execute("https://api.myjson.com/bins/y2a3k");

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(this.requestCode == requestCode) {
            if(resultCode == RESULT_OK) {
                Toast.makeText(getActivity(), "Rezervare a fost facuta pentru: \n" + data.getStringExtra("Rezervare"), Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getActivity(), "Rezervare a fost anulata", Toast.LENGTH_LONG).show();
            }
        }
    }
}
