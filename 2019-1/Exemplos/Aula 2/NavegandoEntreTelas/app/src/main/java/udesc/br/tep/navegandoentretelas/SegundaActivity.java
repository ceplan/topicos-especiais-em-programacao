package udesc.br.tep.navegandoentretelas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SegundaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda);

        //Ler parâmetros
        Bundle params = getIntent().getExtras();
        String cidade = params.getString("cidade");

        //Coloca num label
        TextView txtParam = (TextView) findViewById(R.id.txtParametro);
        txtParam.setText(cidade);
    }
}
