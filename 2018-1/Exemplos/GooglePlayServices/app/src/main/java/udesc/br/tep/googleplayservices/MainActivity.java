package udesc.br.tep.googleplayservices;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.drive.Drive;
import com.google.android.gms.drive.DriveApi;
import com.google.android.gms.drive.query.Filters;
import com.google.android.gms.drive.query.Query;
import com.google.android.gms.drive.query.SearchableField;

public class MainActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Código de exemplo GoogleApiClient
        GoogleApiClient mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* Activity */,
                this /* OnConnectionFailedListener */)
                .addApi(Drive.API)
                .addScope(Drive.SCOPE_FILE)
                .build();

        String filename = "";
        Query query = new Query.Builder()
                .addFilter(Filters.eq(SearchableField.TITLE, filename))
                .build();
        PendingResult<DriveApi.MetadataBufferResult> result = Drive.DriveApi.query(mGoogleApiClient, query);
    }

    @Override
    public void onConnectionFailed(ConnectionResult result) {
        // Erro não resolvido e a conexão com a API não pode ser estabelecida
    }
}
