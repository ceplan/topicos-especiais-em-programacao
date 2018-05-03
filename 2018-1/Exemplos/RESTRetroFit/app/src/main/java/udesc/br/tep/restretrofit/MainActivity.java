package udesc.br.tep.restretrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import udesc.br.tep.restretrofit.pojo.Coin;
import udesc.br.tep.restretrofit.retrofit.RetrofitConfig;

public class MainActivity extends AppCompatActivity {
    private EditText edtCoin;
    private TextView txtResposta;
    private Button btnBuscar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtCoin = findViewById(R.id.edtCoin);
        txtResposta = findViewById(R.id.txtResposta);
        btnBuscar = findViewById(R.id.btnBuscar);

        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<Coin> call = new RetrofitConfig().getCoinService().buscarTickerCoin(edtCoin.getText().toString());
                call.enqueue(new Callback<Coin>() {
                    @Override
                    public void onResponse(Call<Coin> call, Response<Coin> response) {
                        Coin ticker = response.body();
                        txtResposta.setText(ticker.toString());
                    }

                    @Override
                    public void onFailure(Call<Coin> call, Throwable t) {
                        Log.e("CoinService   ", "Erro ao buscar os dados de negociação da moeda:" + t.getMessage());
                    }
                });
            }
        });
    }
}
