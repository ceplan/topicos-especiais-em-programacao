package udesc.br.tep.meuprimeiroapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView tvHelloWorld;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvHelloWorld = findViewById(R.id.txtTeste);
        tvHelloWorld.setText("Teste Aula TEP");
    }
}
