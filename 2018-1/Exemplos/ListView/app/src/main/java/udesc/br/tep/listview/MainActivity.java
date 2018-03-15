package udesc.br.tep.listview;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MainActivity extends AppCompatActivity {
    private ListView listaNomes;
    private EditText edtNome, edtPesquisar;
    private Button btnAdicionar, btnRemover, btnLimpar, btnPesquisar;

    //Itens da lista
    //Também pode-se utilizar Array
    private List<String> itens = new ArrayList<String>();
    private ArrayAdapter<String> adapter = null;
    private int posicaoSelecionada = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Instanciar os componentes
        listaNomes = findViewById(R.id.listaNomes);
        edtNome = findViewById(R.id.edtNome);
        edtPesquisar = findViewById(R.id.edtPesquisar);
        btnAdicionar = findViewById(R.id.btnAdicionar);
        btnRemover = findViewById(R.id.btnRemover);
        btnLimpar = findViewById(R.id.btnLimpar);
        btnPesquisar = findViewById(R.id.btnPesquisar);

        //Adicionar os itens de exemplo
        itens.add("Luís");
        itens.add("Rodrigo");
        itens.add("Luana");
        itens.add("Ana");

        //Adicionar um Adapter
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, itens);
        listaNomes.setAdapter(adapter);

        //Eventos da Lista
        listaNomes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                posicaoSelecionada = position;

                removerItem();
            }
        });

        //Evento do botão
        btnAdicionar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                adicionarItem();
            }
        });

        btnRemover.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                removerItem();
            }
        });

        btnLimpar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                removerItensDaLista();
            }
        });

        btnPesquisar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                pesquisar();
            }
        });

        edtPesquisar.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                pesquisar();
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void removerItem(){
        //Exibir confirmação
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Mensagem");
        builder.setMessage("Quer remover o nome " + itens.get(posicaoSelecionada) + " ?");

        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

                if (posicaoSelecionada == -1){
                    Toast.makeText(MainActivity.this, "Selecione um nome...",
                            Toast.LENGTH_SHORT).show();
                }else {
                    //Remover o item
                    itens.remove(posicaoSelecionada);

                    //Avisar o adapter
                    adapter.notifyDataSetChanged();

                    //Mudar item
                    posicaoSelecionada = -1;
                }
            }
        });

        builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                //Não faz nada
                dialog.dismiss();
            }
        });

        builder.create().show();
    }

    public void removerItensDaLista(){
        //Apagar tudo
        itens.clear();

        //Avisar o adapter
        adapter.notifyDataSetChanged();
    }

    private void adicionarItem(){
        //Atribui o texto para uma variável nome
        String nome = edtNome.getText().toString();

        //Adicionar na lista
        itens.add(nome);

        //Apagar o conteúdo do EdtNome
        edtNome.setText("");

        //Focar no campo de nome
        edtNome.requestFocus();
    }

    private void pesquisar(){
        //Texto a ser pesquisado
        String txtPesquisa = edtPesquisar.getText().toString();
        List<String> itensTemp = new ArrayList<String>();

        //Texto vazio exibe todos
        if(txtPesquisa.trim().isEmpty()){
            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, itens);
            listaNomes.setAdapter(adapter);
        }else{
            //Faz a pesquisa
            itensTemp = itens.stream()
                        // .filter(a -> a.equals(txtPesquisa))//busca igual
                        .filter(a -> a.contains(txtPesquisa))//busca por fragmentos
                        //.filter(a -> a.startsWith(txtPesquisa))//começa por
                        //.filter(a -> a.endsWith(txtPesquisa))//termina em
                        .collect(Collectors.toList());

            //Código sem usar Stream API
            /*
            for(String item:itens){
                if(item.equals(txtPesquisa))
                    itensTemp.add(item);
            }
            */

            //Setar
            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, itensTemp);
            listaNomes.setAdapter(adapter);
        }
    }
}
