package udesc.br.tep.exemplothreadhandlerasync;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Handler handler = new MeuHandler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Teste do handler e send message
        Button btnHandler = findViewById(R.id.btnTesteHandler);
        btnHandler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Cria a mensagem
                Message msg = new Message();
                msg.what = MinhaConstante.MENSAGEM_TESTE;

                // Envia a mensagem com um atraso de 3ms
                handler.sendMessageDelayed(msg, 3000);
            }
        });

        Button btnPost = findViewById(R.id.btnTestePost);
        Handler handlerPadrao = new Handler();
        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Envia a mensagem com um atraso de 3ms
                handler.postAtTime(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getBaseContext(), "A mensagem chegou através de um Runnable!",
                                Toast.LENGTH_SHORT).show();

                        System.out.println(System.currentTimeMillis());
                    }
                }, 3000);


            }
        });

    }
}
