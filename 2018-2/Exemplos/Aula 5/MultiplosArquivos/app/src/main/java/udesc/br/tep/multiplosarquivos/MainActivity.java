package udesc.br.tep.multiplosarquivos;

import android.Manifest;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final int PERMISSAO_ESCRITA = 0;
    private static final int PERMISSAO_LEITURA = 0;
    private EditText edtNomeArq;
    private EditText edtTxtSalvar;
    private EditText edtTxtCarregar;
    private Button btnSalvar;
    private Button btnCarregar;
    private Spinner spnFiles;
    private List<String> listaArquivos;


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

        //Componentes
        edtNomeArq = findViewById(R.id.edtNomeArq);
        edtTxtCarregar = findViewById(R.id.edtTxtCarregar);
        edtTxtSalvar = findViewById(R.id.edtTxtSalvar);
        btnSalvar = findViewById(R.id.btnSalvar);
        btnCarregar = findViewById(R.id.btnCarregar);
        spnFiles = findViewById(R.id.spnFiles);

        //Eventos
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                salvarArquivo(edtNomeArq.getText().toString(), edtTxtSalvar.getText().toString());
            }
        });

        btnCarregar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                lerArquivo(edtNomeArq.getText().toString());
            }
        });

        //Listar arquivos
        listarArquivos();
    }

    private void salvarArquivo(String nomeArq, String texto){
        //Verificação inicial - escrita
        if (ehPossivelEscrever()){
            File file = new File(Environment.getExternalStorageDirectory(), nomeArq);
            FileOutputStream outputStream;

            try {
                //Abre o arquivo para escrita
                outputStream = new FileOutputStream(file);

                //Salva o conteúdo da string no arquivo
                outputStream.write(texto.getBytes());

                //Fecha o arquivo
                outputStream.close();

                //Exibe mensagem
                exibirMensagem("Arquivo salvo!");

                //Carregar
                listarArquivos();
            } catch (Exception e) {
                Log.e("Arquivo", "Erro ao escrever no arquivo: " + e.getMessage());
                Log.
                exibirMensagem("Erro ao escrever!");
            }

        }else{
            exibirMensagem("Não posso escrever!");
            Log.e("Permissão", "Não posso escrever!");
        }

    }

    private void lerArquivo(String nomeArq){
        //Verificação inicial - leitura
        if (ehPossivelLer()){
            File file = new File(Environment.getExternalStorageDirectory(), nomeArq);

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

                    edtTxtCarregar.setText(sb.toString()); //Exibe no txt
                    exibirMensagem("Arquivo lido!");//Exibe mensagem
                    bufferedReader.close();//Fechar
                } catch (Exception e) {
                    Log.e("Arquivo", "Erro ao ler conteúdo do arquivo: " + e.getMessage());
                    exibirMensagem("Erro ao ler!");
                }
            }else{
                exibirMensagem("Arquivo não existe!");
                return;
            }
        }else{
            exibirMensagem("Não posso ler!o");
            Log.e("Permissão", "Não posso ler!");
        }

    }


    //Carrega o nome dos arquivos existentes p/ o Spinner
    private void listarArquivos(){
        File diretorio = new File(Environment.getExternalStorageDirectory().toString());
        File[] arquivos = diretorio.listFiles();
        listaArquivos = new ArrayList<String>();

        if(arquivos != null)
        {
            int length = arquivos.length;

            for(int i = 0; i < length; ++i)
            {
                File f = arquivos[i];
                if (f.isFile())
                {
                    listaArquivos.add(f.getName());
                }
            }

            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>
                    (this,android.R.layout.simple_dropdown_item_1line, listaArquivos);

            spnFiles.setAdapter(arrayAdapter);
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
