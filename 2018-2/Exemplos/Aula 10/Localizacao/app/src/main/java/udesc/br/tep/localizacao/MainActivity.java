package udesc.br.tep.localizacao;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

public class MainActivity extends AppCompatActivity {
    private FusedLocationProviderClient mFusedLocationClient;
    private TextView txtLocalizacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtLocalizacao = findViewById(R.id.txtLocalizacao);

        // Mostrar a distancia entre 2 pontos
        float results[] = new float[3];
        Location.distanceBetween(
                -26.242910,
                -49.512308,
                -26.239751,
                -49.506260,
                results
        );
        System.out.println("Distância: " + results[2]);

        // Provedor de localização do Android
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                    100);
        } else {
            mFusedLocationClient.getLastLocation()
                    .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            // Obtem a última localizacação,
                            // porém devemos verificar se é nula (só em alguns casos ocorre)
                            // Ex: localização desabilitada no dispositivo
                            if (location != null) {
                                // Trabalhar com a localização
                                txtLocalizacao.setText(location.toString());
                            }
                        }
                    });
        }


    }
}
