package udesc.br.tep.sqlitenoandroid.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import udesc.br.tep.sqlitenoandroid.helper.SQLiteHelper;

/**
 * Classe que obtém o DB recém criado.
 */
public class DBUtil {
    // Singleton instance
    private static DBUtil instance = null;

    // Delete SQL
    private static String deleteSQL = "DROP TABLE IF EXISTS carro";
    // Create SQL
    private static String[] createSQL = new String[]{
            "CREATE TABLE carro (id integer primary key autoincrement," +
                                 "nome text not null," +
                                 "placa text not null," +
                                 "ano integer);",
            "INSERT INTO carro(nome,placa,ano) VALUES('Peugeot 207','QOH-1999', 2013);",
            "INSERT INTO carro(nome,placa,ano) VALUES('VW Jetta TSI','MKX-0001', 2011);",
            "INSERT INTO carro(nome,placa,ano) VALUES('Chevette','QHH-1999', 1970);"
    };
    // Nome do Banco
    private static String nomeBanco = "aulatep";
    // Versão do Banco
    private static int versaoBanco = 1;
    // Helper
    private static SQLiteHelper dbHelper;
    // Banco de Dados
    private static SQLiteDatabase db;

    public DBUtil(){
    }

    public static DBUtil getInstance(Context ctx){
        if (instance == null){

            //Criar o banco
            dbHelper = new SQLiteHelper(ctx, nomeBanco, versaoBanco, createSQL, deleteSQL);
            db = dbHelper.getWritableDatabase();

            instance = new DBUtil();
        }

        return instance;
    }

    public static void fechar(){
        db.close();
        if (dbHelper != null){
            dbHelper.close();
        }
    }

    public static SQLiteDatabase getDb(){
        return db;
    }


}
