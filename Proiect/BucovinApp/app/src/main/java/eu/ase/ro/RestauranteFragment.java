package eu.ase.ro;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class RestauranteFragment extends Fragment {

    private List<Restaurant> restaurante;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.restaurante_fragment,container,false);
        restaurante = new ArrayList<>();

        GetJSONRestaurante getJSONRestaurante = new GetJSONRestaurante() {
            @Override
            protected void onPostExecute(List<Restaurant> restaurants) {
                for(Restaurant restaurant : restaurants) {
                    restaurante.add(restaurant);
                }

                ListView listView = view.findViewById(R.id.restauranteListViewID);
                RestaurantAdapter restaurantAdapter = new RestaurantAdapter(getActivity(),R.layout.restaurant_item_layout, restaurante);
                listView.setAdapter(restaurantAdapter);
            }
        };
        getJSONRestaurante.execute("https://api.myjson.com/bins/8r1v2");
        return view;
    }
}
