package udesc.br.tep.sqlitenoandroid;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;
import java.util.ListIterator;

import udesc.br.tep.sqlitenoandroid.model.Carro;
import udesc.br.tep.sqlitenoandroid.model.CarroDAO;
import udesc.br.tep.sqlitenoandroid.util.DBUtil;

public class MainActivity extends AppCompatActivity {
    private Button btnPrimeiro;
    private Button btnProximo;
    private Button btnAnterior;
    private Button btnUltimo;
    private Button btnNovo;
    private Button btnSalvar;
    private Button btnRemover;
    private EditText edtId;
    private EditText edtNome;
    private EditText edtPlaca;
    private EditText edtAno;

    // Lista
    private List<Carro> carros;
    private ListIterator<Carro> iterator;
    private Carro carroAtual = null;

    // Dao
    private CarroDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**Exemplo na memória**/
        String query = "select sqlite_version() AS sqlite_version";
        SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(":memory:", null);
        Cursor cursor = db.rawQuery(query, null);
        String sqliteversion = "";

        if (cursor.moveToNext()){
            sqliteversion = cursor.getString(0);
        }

        Log.i("Information", sqliteversion);

        //Criar tabela
        db.execSQL("CREATE TABLE teste (id integer primary key autoincrement, nome text);");

        //Inserir
        db.execSQL("INSERT INTO teste(nome) VALUES('Luis'),('Marco'),('Rodrigo');");

        // Recuperar registros
        Cursor c = db.rawQuery("SELECT * FROM teste", null);
        if(!c.moveToFirst()){return;}

        do{
            Log.i("INFORMATION", "Nome: " + c.getString(1));
        }while(c.moveToNext());

        /** Fim do exemplo na memória **/


        // Botões e campos
        btnPrimeiro = findViewById(R.id.btnPrimeiro);
        btnProximo = findViewById(R.id.btnProximo);
        btnAnterior = findViewById(R.id.btnAnterior);
        btnUltimo = findViewById(R.id.btnUltimo);
        btnNovo = findViewById(R.id.bNovo);
        btnSalvar = findViewById(R.id.bSalvar);
        btnRemover = findViewById(R.id.bExcluir);
        edtId = findViewById(R.id.edtCodigo);
        edtId.setEnabled(false);
        edtNome = findViewById(R.id.edtNome);
        edtPlaca = findViewById(R.id.edtPlaca);
        edtAno = findViewById(R.id.edtAno);

        // DAO
        DBUtil dbutil = DBUtil.getInstance(this.getApplicationContext());
        dao = new CarroDAO(dbutil.getDb());

        //Carregar os itens iniciais e exibir o primeiro
        carregarLista();

        // Eventos de navegação
        btnPrimeiro.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // iterar sobre a lista
                while(iterator.hasPrevious()){
                    carroAtual = iterator.previous();
                }

                // exibe o carro
                mostrarCarro(carroAtual);
            }
        });

        btnAnterior.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // iterar sobre a lista
                if(iterator.hasPrevious()) {
                    carroAtual = iterator.previous();

                    // exibe o carro
                    mostrarCarro(carroAtual);
                }
            }
        });

        btnProximo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // iterar sobre a lista
                if(iterator.hasNext()) {
                    carroAtual = iterator.next();

                    // exibe o carro
                    mostrarCarro(carroAtual);
                }
            }
        });

        btnUltimo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // iterar sobre a lista
                while(iterator.hasNext()){
                    carroAtual = iterator.next();
                }

                // exibe o carro
                mostrarCarro(carroAtual);
            }
        });

        // Eventos de inserção
        btnNovo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Limpar campos
                edtId.setText("-1");
                edtNome.setText("");
                edtPlaca.setText("");
                edtAno.setText("");
                carroAtual = null;

                //Focar no nome
                edtNome.requestFocus();

                //Habilitar escrita
                setEscrita();
            }
        });

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (carroAtual == null)
                    carroAtual = new Carro();

                // Armazena dados
                carroAtual.setNome(edtNome.getText().toString());
                carroAtual.setPlaca(edtPlaca.getText().toString());
                carroAtual.setAno(edtAno.getText().toString());

                // Verifica se tem id
                if (carroAtual.getId() != 0)
                    dao.alterar(carroAtual);
                else
                    dao.inserir(carroAtual);

                //Atualizar
                carregarLista();
                setLeitura();
            }
        });

        btnRemover.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Remover
                dao.excluir(carroAtual.getId());

                //Atualizar lista
                carregarLista();
            }
        });
    }

    private void carregarLista(){
        carros = dao.lista();
        iterator = carros.listIterator();

        if (carros.size() > 0) {
            carroAtual = carros.get(0);
        }else
            carroAtual = null;

        mostrarCarro(carroAtual);
    }

    private void mostrarCarro(Carro carro){
        if (carro != null){
            edtId.setText(String.valueOf(carro.getId()));
            edtNome.setText(carro.getNome());
            edtAno.setText(carro.getAno());
            edtPlaca.setText(carro.getPlaca());

            //Habilita leitura
            setLeitura();
        }
    }

    /**
     * Habilita os componentes leitura, ou seja, habilita a navegação
     */
    private void setLeitura(){
        boolean flag = true;

        if (carros.size() > 0)
            flag = true;
        else
            flag = false;

        btnAnterior.setEnabled(flag);
        btnProximo.setEnabled(flag);
        btnPrimeiro.setEnabled(flag);
        btnUltimo.setEnabled(flag);

        btnNovo.setEnabled(true);
        btnSalvar.setEnabled(flag);
        btnRemover.setEnabled(flag);
    }

    /**
     * Habilita os componentes escrita, ou seja, habilita a navegação
     */
    private void setEscrita(){
        btnAnterior.setEnabled(false);
        btnProximo.setEnabled(false);
        btnPrimeiro.setEnabled(false);
        btnUltimo.setEnabled(false);

        btnNovo.setEnabled(false);
        btnSalvar.setEnabled(true);
        btnRemover.setEnabled(false);

    }
}
