package udesc.br.tep.metodosimportantes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //Exemplo
    private EditText txtNome;
    private Button btnExibir;
    private Button btnFechar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        //Setar o edittext
        txtNome =  (EditText) findViewById(R.id.edtTxtNome);

        //Setar o botão
        btnExibir = (Button) findViewById(R.id.btnExibir);
        btnFechar = (Button) findViewById(R.id.btnFechar);

        final EditText edtNomeDigitado = (EditText) findViewById(R.id.edtNomeDigitado);
        edtNomeDigitado.setEnabled(false);

        //Evento do botão
        btnExibir.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                //Exibir o nome
                exibirMensagem(txtNome.getText().toString());
                edtNomeDigitado.setText(txtNome.getText().toString());
            }
        });

        //Evento do botão
        btnFechar.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                encerrar();
            }
        });
    }

    private void exibirMensagem(String mensagem){
        Toast toast = Toast.makeText(this, mensagem, Toast.LENGTH_LONG);
        toast.show();
    }

    private void encerrar(){
        this.finish();
    }
}
