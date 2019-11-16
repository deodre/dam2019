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

public class ObiectivTuristicAdapter extends ArrayAdapter<ObiectivTuristic> {
    private int resursaID;

    public ObiectivTuristicAdapter(@NonNull Context context, int resource, @NonNull List<ObiectivTuristic> objects) {
        super(context, resource, objects);
        resursaID = resource;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ObiectivTuristic obiectivTuristic = getItem(position);
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(resursaID, null);
        TextView denumireObiectivTuristicTV = view.findViewById(R.id.denumireObiectivTuristicTV);
        TextView tipObiectivTuristicTV = view.findViewById(R.id.tipObiectivTuristicTV);
        TextView anulConstructieiTV = view.findViewById(R.id.anulConstructieiTV);
        denumireObiectivTuristicTV.setText(obiectivTuristic.getDenumire());
        tipObiectivTuristicTV.setText(obiectivTuristic.getTip());
        anulConstructieiTV.setText(("Dateaza din " + obiectivTuristic.getAnul_constructiei()));
        return view;
    }
}
