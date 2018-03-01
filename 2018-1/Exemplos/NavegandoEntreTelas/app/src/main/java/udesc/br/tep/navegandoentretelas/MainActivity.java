package udesc.br.tep.navegandoentretelas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button btnSegundaTela;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Botão
        btnSegundaTela = (Button) findViewById(R.id.btnAbreSegundaTela);
        btnSegundaTela.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SegundaActivity.class);

                //Bundle
                Bundle params = new Bundle();
                params.putString("cidade", "São Bento do Sul");

                intent.putExtras(params);
                // intent.putExtra("nome","Luís");

                // Mostrar a activity
                startActivity(intent);
            }
        });
    }
}
