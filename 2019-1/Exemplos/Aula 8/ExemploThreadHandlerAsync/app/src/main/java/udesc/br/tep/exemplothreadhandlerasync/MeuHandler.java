package udesc.br.tep.exemplothreadhandlerasync;

import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

/**
 * Created by luis on 06/03/18.
 */

public class MeuHandler extends Handler {
    @Override
    public void handleMessage(Message msg) {
        switch(msg.what){
            case MinhaConstante.MENSAGEM_TESTE:
                System.out.println("Chegou");
                System.out.println(System.currentTimeMillis());
                break;
            default: System.out.println("Não conheco a mensagem!");
        }

    }
}
