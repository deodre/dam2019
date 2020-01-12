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

public class RecenzieAdapter extends ArrayAdapter<Recenzie> {

    private int resursaID;

    public RecenzieAdapter(@NonNull Context context, int resource, @NonNull List<Recenzie> objects) {
        super(context, resource, objects);
        resursaID = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Recenzie recenzie = getItem(position);
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(resursaID, null);
        TextView textRecenzieTV = view.findViewById(R.id.textRecenzieTV);
        TextView ratingRecenzieTV = view.findViewById(R.id.ratingRecenzieTV);
        TextView userIdTV = view.findViewById(R.id.userIdTV);
        textRecenzieTV.setText(recenzie.getRecenzie());
        ratingRecenzieTV.setText(String.valueOf(recenzie.getRating()));
        userIdTV.setText(("User #" + recenzie.getUserID()));
        return view;
    }
}
