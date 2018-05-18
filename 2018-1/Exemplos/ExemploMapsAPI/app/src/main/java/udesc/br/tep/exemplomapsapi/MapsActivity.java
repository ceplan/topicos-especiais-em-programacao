package udesc.br.tep.exemplomapsapi;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        // Cria o componente de mapa
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Adiciona um marcador em latitude e longitude
        LatLng sbs = new LatLng(-26.223943, -49.401024);
        mMap.addMarker(new MarkerOptions()
                .position(sbs)
                .title("São Bento do Sul")
                .snippet("Aqui tem UDESC!"));

        // Move a câmera para a posição recém-adicionada
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sbs));
    }
}
