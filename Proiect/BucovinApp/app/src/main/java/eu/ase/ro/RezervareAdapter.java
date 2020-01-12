package eu.ase.ro;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class RezervareAdapter extends ArrayAdapter<Rezervare> {
    private int resursaID;

    public RezervareAdapter(@NonNull Context context, int resource, @NonNull List<Rezervare> objects) {
        super(context, resource, objects);
        resursaID = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Rezervare rezervare = getItem(position);
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(resursaID,null);
        TextView numePrenumeTV = view.findViewById(R.id.numePrenumeTV);
        TextView checkInTV = view.findViewById(R.id.checkInTV);
        TextView checkOutTV = view.findViewById(R.id.checkOutTV);
        TextView familieCopilTV = view.findViewById(R.id.familieCopilTV);
        numePrenumeTV.setText(rezervare.getNume() + " " + rezervare.getPrenume());
        checkInTV.setText(rezervare.getCheckin());
        checkOutTV.setText(rezervare.getCheckout());
        if(rezervare.isFamilieCopil() == true) {
            familieCopilTV.setText("Familie cu copil");
        }
        else {
            familieCopilTV.setText("Familie fara copil");
        }
        return view;
    }

}
