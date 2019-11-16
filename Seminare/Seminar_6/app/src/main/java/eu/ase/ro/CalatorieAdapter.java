package eu.ase.ro;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.List;

public class CalatorieAdapter extends ArrayAdapter<Calatorie> {
    private int resursaID;
    public CalatorieAdapter(@NonNull Context context, int resource, @NonNull List<Calatorie> objects) {
        super(context, resource, objects);
        resursaID = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Calatorie c = getItem(position);
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(resursaID,null);
        TextView destinatieTV = view.findViewById(R.id.destinatieTV);
        TextView durataTV = view.findViewById(R.id.durataTV);
        TextView dataPlecareTV = view.findViewById(R.id.dataPlecareTV);

        destinatieTV.setText(c.getDestinatie());
        durataTV.setText("" +c.getNrZile());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-YYYY");
        dataPlecareTV.setText(simpleDateFormat.format(c.getDataPlecare()));
        return view;

    }
}
