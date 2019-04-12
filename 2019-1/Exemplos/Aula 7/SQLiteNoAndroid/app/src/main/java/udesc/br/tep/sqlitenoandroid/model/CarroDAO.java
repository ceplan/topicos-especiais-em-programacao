package udesc.br.tep.sqlitenoandroid.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import udesc.br.tep.sqlitenoandroid.util.GenericDAO;

public class CarroDAO implements GenericDAO<Carro> {
    private SQLiteDatabase db;

    public CarroDAO(SQLiteDatabase db){
        this.db = db;
    }

    @Override
    public long inserir(Carro objeto) {
        // Preencher os elementos
        ContentValues values = new ContentValues();
        values.put("nome", objeto.getNome());
        values.put("placa", objeto.getPlaca());
        values.put("ano", objeto.getAno());

        // Salvar no banco
        // INSERT INTO carro (nome,placa,ano) VALUES ()
        long id = db.insert("carro", "", values);

        return id;
    }

    @Override
    public int alterar(Carro objeto) {
        // Preencher os valores
        ContentValues values = new ContentValues();
        values.put("nome", objeto.getNome());
        values.put("placa", objeto.getPlaca());
        values.put("ano", objeto.getAno());

        String[] whereArgs = {String.valueOf(objeto.getId())};

        // UPDATE carro SET campo=? WHERE id=?
        int count = db.update("carro", values, "id=?", whereArgs);

        Log.i("Alteração", "Alterou " + count + " registro(s).");

        return count;
    }

    @Override
    public int excluir(int id) {
        // DELETE FROM carro WHERE id=?
        int count = db.delete("carro", "id=?", new String[]{String.valueOf(id)});
        Log.i("Exclusão", "Deletou " + count + " registro(s).");

        return count;
    }

    @Override
    public Carro buscar(int id) {
        Cursor c = null;

        try{
            // SELECT * FROM carro WHERE id=?
            c = db.query("carro", new String[]{"nome", "placa", "ano"}, "id = ?", new String[]{String.valueOf(id)},
                    null, null, null);

            if (c.getCount() > 0){
                // Posiciona no primeiro elemento
                c.moveToFirst();

                Carro carro = new Carro();

                // Lê os dados
                carro.setId(id);
                carro.setNome(c.getString(1));
                carro.setPlaca(c.getString(2));
                carro.setAno(c.getString(3));

                return carro;
            }

        }catch(Exception e){
            return null;
        }

        return null;
    }

    @Override
    public List<Carro> lista() {
        // Lista de carros
        List<Carro> carros = null;

        try{
            // SELECT * FROM carro
            Cursor c = db.query("carro", new String[]{"id", "nome", "placa", "ano"}, null, null,null,
                    null, null);

            if (c.moveToFirst()){
                // Criar a lista
                carros = new ArrayList<Carro>();

                // Faz loop até o final
                do{
                    Carro carro = new Carro();

                    // Recuperar os atributos do carro
                    carro.setId((int)c.getLong(0));
                    carro.setNome(c.getString(1));
                    carro.setPlaca(c.getString(2));
                    carro.setAno(c.getString(3));

                    // Adicionar na lista
                    carros.add(carro);

                }while(c.moveToNext());
            }

        }catch(Exception e){
            return null;
        }

        return carros;
    }
}
