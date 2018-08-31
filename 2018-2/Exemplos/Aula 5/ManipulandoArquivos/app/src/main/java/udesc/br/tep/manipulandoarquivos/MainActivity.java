package udesc.br.tep.manipulandoarquivos;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    private static final int PERMISSAO_ESCRITA = 0;
    private static final int PERMISSAO_LEITURA = 0;
    private Button btnSalvarI;
    private Button btnLerI;
    private Button btnSalvarE;
    private Button btnLerE;
    private EditText edtNome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Permissões
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                PERMISSAO_ESCRITA);

        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                PERMISSAO_LEITURA);

        //Atribuição dos componentes
        btnSalvarI = findViewById(R.id.btnSalvarI);
        btnLerI = findViewById(R.id.btnLerI);
        btnSalvarE = findViewById(R.id.btnSalvarE);
        btnLerE = findViewById(R.id.btnLerE);
        edtNome = findViewById(R.id.edtNome);

        //Eventos dos botões
        btnSalvarI.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                salvarNoInterno(edtNome.getText().toString());
            }
        });

        btnSalvarE.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                salvarNoExterno(edtNome.getText().toString());
            }
        });

        btnLerI.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                lerDoInterno();
            }
        });

        btnLerE.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                lerDoExterno();
            }
        });

    }

    private void salvarNoInterno(String nome){
        FileOutputStream outputStream;

        try {
            //Abre o arquivo para escrita
            outputStream = openFileOutput("arquivo.txt", Context.MODE_PRIVATE);

            //Salva o conteúdo da string no arquivo
            outputStream.write(nome.getBytes());

            //Fecha o arquivo
            outputStream.close();

            //Exibe mensagem
            exibirMensagem("Arquivo salvo! - Arm. Interno");
        } catch (Exception e) {
            Log.e("Arquivo", "Erro ao escrever no arquivo: " + e.getMessage());
            exibirMensagem("Erro ao escrever! - Arm. Interno");
        }
    }


    private void lerDoInterno(){
        //Contexto da aplicação
        Context context = getApplicationContext();

        //Verifica se o arquivo existe
        File file = new File(context.getFilesDir() + "/" + "arquivo.txt");

        //Arquivo não existe
        if (file.exists()) {
            InputStreamReader inputStreamReader;
            BufferedReader bufferedReader;

            try {
                //Realiza a leitura do arquivo
                inputStreamReader = new InputStreamReader(context.openFileInput("arquivo.txt"));
                bufferedReader = new BufferedReader(inputStreamReader);

                StringBuilder sb = new StringBuilder();

                String line;

                //Faz a leitura enquanto o arquivo não chega ao fim
                while ((line = bufferedReader.readLine()) != null) {
                    //Faz o append no stringbuilder, ou seja é igual a string = string + novo
                    sb.append(line);
                }

                //Exibe no txt
                edtNome.setText(sb.toString());

                //Exibe mensagem
                exibirMensagem("Arquivo lido! - Arm. Interno");

                //Fechar
                inputStreamReader.close();
            } catch (Exception e) {
                Log.e("Arquivo", "Erro ao ler conteúdo do arquivo: " + e.getMessage());
                exibirMensagem("Erro ao ler! - Arm. Interno");
            }
        }else{
            exibirMensagem("Arquivo não existe! - Arm. Interno");
            return;
        }
    }


    private void salvarNoExterno(String nome){
        //Verificação inicial - escrita
        if (ehPossivelEscrever()){
            File file = new File(Environment.getExternalStorageDirectory(), "arquivo.txt");
            FileOutputStream outputStream;

            try {
                //Abre o arquivo para escrita
                outputStream = new FileOutputStream(file);

                //Salva o conteúdo da string no arquivo
                outputStream.write(nome.getBytes());

                //Fecha o arquivo
                outputStream.close();

                //Exibe mensagem
                exibirMensagem("Arquivo salvo! - Arm. Externo");
            } catch (Exception e) {
                Log.e("Arquivo", "Erro ao escrever no arquivo: " + e.getMessage());
                exibirMensagem("Erro ao escrever! - Arm. Externo");
            }

        }else{
            exibirMensagem("Não posso escrever!");
            Log.e("Permissão", "Não posso escrever!");
        }
    }


    private void lerDoExterno(){
        //Verificação inicial - leitura
        if (ehPossivelLer()){
            File file = new File(Environment.getExternalStorageDirectory(), "arquivo.txt");
            if (file.exists()) {//Arquivo não existe
                BufferedReader bufferedReader;
                try {
                    bufferedReader = new BufferedReader(new FileReader(file));//Le o arquivo
                    StringBuilder sb = new StringBuilder();
                    String line;

                    //Faz a leitura enquanto o arquivo não chega ao fim
                    while ((line = bufferedReader.readLine()) != null) {
                        //Faz o append no stringbuilder, ou seja é igual a string = string + novo
                        sb.append(line);
                    }

                    edtNome.setText(sb.toString()); //Exibe no txt
                    exibirMensagem("Arquivo lido! - Arm. Externo");//Exibe mensagem
                    bufferedReader.close();//Fechar
                } catch (Exception e) {
                    Log.e("Arquivo", "Erro ao ler conteúdo do arquivo: " + e.getMessage());
                    exibirMensagem("Erro ao ler! - Arm. Externo");
                }
            }else{
                exibirMensagem("Arquivo não existe! - Arm. Externo");
                return;
            }
        }else{
            exibirMensagem("Não posso ler! - Arm. Externo");
            Log.e("Permissão", "Não posso ler!");
        }
    }

    //Exibe mensagem, passada como parâmetro, através de uma Toast
    private void exibirMensagem(String msg){
        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_LONG).show();
    }

    //Verifica se o dispositivo externo está acessível para escrita
    public boolean ehPossivelEscrever() {
        String estado = Environment.getExternalStorageState();

        if (Environment.MEDIA_MOUNTED.equals(estado)) {
            return true;
        }

        return false;
    }

    //Verifica se o dispositivo externo está acessível para leitura
    public boolean ehPossivelLer() {
        String estado = Environment.getExternalStorageState();

        if (Environment.MEDIA_MOUNTED.equals(estado) ||
                 Environment.MEDIA_MOUNTED_READ_ONLY.equals(estado)) {
            return true;
        }

        return false;
    }

}
