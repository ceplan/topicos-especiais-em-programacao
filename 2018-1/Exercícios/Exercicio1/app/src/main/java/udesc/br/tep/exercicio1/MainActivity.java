package udesc.br.tep.exercicio1;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText edtCodigo;
    private EditText edtNome;
    private EditText edtEndereco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtCodigo = findViewById(R.id.edtCodigo);
        edtNome = findViewById(R.id.edtNome);
        edtEndereco = findViewById(R.id.edtEndereco);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_mainactivity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_limpar:
                edtCodigo.setText("");
                edtNome.setText("");
                edtEndereco.setText("");
                break;
            case R.id.action_enviar:
                Intent chamarTela = new Intent(MainActivity.this, SegundaTela.class);
                Bundle params = new Bundle();
                params.putString("nome", edtNome.getText().toString());
                params.putString("codigo", edtCodigo.getText().toString());
                params.putString("endereco", edtEndereco.getText().toString());

                chamarTela.putExtras(params);
                startActivity(chamarTela);
            default:
                break;
        }

        return true;
    }
}
