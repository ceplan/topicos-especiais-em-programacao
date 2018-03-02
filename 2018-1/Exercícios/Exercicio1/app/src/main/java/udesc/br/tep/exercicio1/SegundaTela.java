package udesc.br.tep.exercicio1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class SegundaTela extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda_tela);

        Bundle params = getIntent().getExtras();

        TextView txtDados = (TextView) findViewById(R.id.txtDados);
        String texto = params.getString("codigo") + "\n";
        texto += params.getString("nome") + "\n";
        texto += params.getString("endereco") + "\n";
        txtDados.setText(texto);
    }
}
