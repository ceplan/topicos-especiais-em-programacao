package udesc.br.tep.restehttpservice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;
import java.util.concurrent.ExecutionException;

import udesc.br.tep.restehttpservice.pojo.CEP;
import udesc.br.tep.restehttpservice.pojo.marcas;
import udesc.br.tep.restehttpservice.service.HttpService;

public class MainActivity extends AppCompatActivity {
    private EditText edtCep;
    private Button btnCep;
    private TextView txtResposta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtCep = findViewById(R.id.edtCep);
        btnCep = findViewById(R.id.btnBuscarCEP);
        txtResposta = findViewById(R.id.txtResposta);

        btnCep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Buscar o CEP via HTTPService
                String cep_digitado = edtCep.getText().toString();
                HttpService httpService = new HttpService(cep_digitado);

                try {
                    CEP retorno = httpService.execute().get();
                    txtResposta.setText(retorno.toString());

                } catch (InterruptedException e) {
                    Log.e("Erro", "Erro na execução do HTTP Service.");
                } catch (ExecutionException e) {
                    Log.e("Erro", "Erro na execução do HTTP Service.");
                }
            }
        });
    }
}
