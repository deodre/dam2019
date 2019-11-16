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

public class RestaurantAdapter extends ArrayAdapter<Restaurant> {
    private int resurseID;

    public RestaurantAdapter(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
        resurseID = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Restaurant restaurant = getItem(position);
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(resurseID, null);
        TextView denumireRestaurantTV = view.findViewById(R.id.denumireRestaurantTV);
        TextView programRestaurantTV = view.findViewById(R.id.programRestaurantTV);
        TextView adresaRestaurantTV = view.findViewById(R.id.adresaRestaurantTV);
        denumireRestaurantTV.setText(restaurant.getDenumire());
        programRestaurantTV.setText(restaurant.getProgram());
        adresaRestaurantTV.setText(restaurant.getAdresa());
        return view;
    }
}
