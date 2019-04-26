package udesc.br.tep.sockets_android;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {
    private Conexao conexao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Componentes
        final EditText edtMsg = findViewById(R.id.edtMsg);
        Button btnEnviar = findViewById(R.id.btnEnviar);
        Button btnFechar = findViewById(R.id.btnFechar);


        // Cria o objeto de conexao
        // ip 127.0.0.1 - LOCALHOST - dá erro, pois é usado no emulador do Android
        conexao = new Conexao("10.70.1.74", 12345);
        conexao.execute();

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Envia a mensagem
                new Mensagem(conexao.saida, edtMsg.getText().toString()).execute();
            }
        });

        btnFechar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Envia a mensagem
                conexao.fecharConexao();
            }
        });

    }

    private class Mensagem extends AsyncTask<Void, Void, Void> {
        public PrintStream saida;
        public String mensagem;

        public Mensagem(PrintStream saida, String mensagem){
            this.saida = saida;
            this.mensagem = mensagem;
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            saida.println(mensagem);
            return null;
        }
    }

    private class Conexao extends AsyncTask<Void, Void, Void> {
        private String ip;
        private int porta;
        public PrintStream saida;
        public BufferedReader entrada;
        public Socket cliente;

        public Conexao(String ip, int porta){
            this.ip = ip;
            this.porta = porta;
        }
        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            try {
                // Faz a conexão
                cliente = new Socket(ip, porta);

                // Envia os dados
                saida = new PrintStream(cliente.getOutputStream());
                entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            } catch(IOException e){
                Log.e("Erro", "Erro ao se conectar " + e.getMessage());
            }

            return null;
        }

        public void fecharConexao(){
            try {
                cliente.close();
                Log.i("INFO", "Conexão fechada");
            } catch (IOException e) {
                e.printStackTrace();
                Log.e("ERR", "Conexão não foi fechada  - erro");
            }
        }

        @Override
        protected void onPostExecute(Void result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);
            // Handle/Update UI Part
        }
    }
}
