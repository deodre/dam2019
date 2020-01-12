package dam.ase.ro;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class CazareAdapter extends ArrayAdapter<Cazare> {
    private int resursaID;

    public CazareAdapter(@NonNull Context context, int resource, @NonNull List<Cazare> objects) {
        super(context, resource, objects);
        resursaID = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Cazare cazare = getItem(position);
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(resursaID,null);
        TextView denumireCazareTV = view.findViewById(R.id.denumireCazareTV);
        TextView tipCazareTV = view.findViewById(R.id.tipCazareTV);
        TextView nrLocuriTV = view.findViewById(R.id.nrLocuriTV);
        TextView adresaCazareTV = view.findViewById(R.id.adresaCazareTV);
        denumireCazareTV.setText(cazare.getDenumire());
        tipCazareTV.setText(cazare.getTip());
        nrLocuriTV.setText(cazare.getNrLocuri() + " locuri");
        adresaCazareTV.setText(cazare.getAdresa());
        return view;
    }

}
