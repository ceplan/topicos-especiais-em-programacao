package udesc.br.tep.sqlitenoandroid.helper;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SQLiteHelper extends SQLiteOpenHelper {
    private String[] scriptSQLCreate;
    private String scriptSQLDelete;

    /**
     * Cria uma instância de SQLiteHelper
     * @param context
     * @param nomeBanco
     * @param versaoBanco
     * @param scriptSQLCreate
     * @param scriptSQLDelete
     */

    public SQLiteHelper(Context context, String nomeBanco, int versaoBanco, String[] scriptSQLCreate,
                        String scriptSQLDelete){
        super(context, nomeBanco, null, versaoBanco);
        this.scriptSQLCreate = scriptSQLCreate;
        this.scriptSQLDelete = scriptSQLDelete;
    }


    @Override
    //Criar um banco novo
    public void onCreate(SQLiteDatabase db) {
        Log.i("SQLiteHelper", "Criando um BD c/ SQL...");
        int qtdeScripts = scriptSQLCreate.length;

        // Executa cada script sql passado como parâmetro
        for (int i = 0; i < qtdeScripts; i++){
            String sql = scriptSQLCreate[i];
            Log.i("SQLiteHelper", "Executando: " + sql);

            //Executa o script
            db.execSQL(sql);
        }
    }

    @Override
    //Mudança de versão
    public void onUpgrade(SQLiteDatabase db, int vAntiga, int vNova) {
        Log.w("Update", "Atualizando versão " + vAntiga + " para " + vNova + "." +
                "Todos os registros serão excluídos.");
        Log.i("Update", scriptSQLDelete);

        // Deletar
        db.execSQL(scriptSQLDelete);

        // Criar
        onCreate(db);
    }
}
