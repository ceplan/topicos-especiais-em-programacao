package udesc.br.tep.outroscomponentes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private Button btnTeste;
    private ImageView imgPinky;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Atribuir
        btnTeste =  findViewById(R.id.btnTeste);
        imgPinky = findViewById(R.id.imgPinky);

        //Evento
        btnTeste.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (imgPinky.getVisibility() == View.VISIBLE)
                    imgPinky.setVisibility(View.INVISIBLE);
                else
                    imgPinky.setVisibility(View.VISIBLE);

            }
        });


    }
}
