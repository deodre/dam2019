package dam.ase.ro;

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
        getJSONRestaurante.execute("https://api.myjson.com/bins/y2a3k");
        return view;
    }
}
