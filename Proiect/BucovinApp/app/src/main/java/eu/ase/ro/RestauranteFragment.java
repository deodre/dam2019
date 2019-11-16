package eu.ase.ro;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

public class RestauranteFragment extends Fragment {

    private List<Restaurant> restaurante;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.restaurante_fragment,container,false);
        restaurante = new ArrayList<>();
        restaurante.add(new Restaurant("Restaurant Bucovina","L-V 07-23","Strada Ana Ipătescu 5, Suceava"));
        restaurante.add(new Restaurant("Latino","L-V 08-23","Strada Curtea Domnească 9, Suceava"));
        restaurante.add(new Restaurant("Centru Vechi","L-V 08-00","Strada Vasile Bumbac 3, Suceava"));

        ListView listView = view.findViewById(R.id.restauranteListViewID);
        RestaurantAdapter restaurantAdapter = new RestaurantAdapter(getActivity(),R.layout.restaurant_item_layout, restaurante);
        listView.setAdapter(restaurantAdapter);

        return view;
    }
}
