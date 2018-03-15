package udesc.br.tep.intent;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.AlarmClock;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private int CODE = 1;
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private Button btnAbrirFone, btnTirarFoto, btnSite, btnLocalizacao, btnEmail, btnAddAlarme;
    private ImageView imagem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Intent explícita - exemplo
        //Intent intent = new Intent(this, MainActivity.class);
        //startActivity(intent);

        //Intent implícita - exemplo
        //Intent intent = new Intent(Intent.ACTION_DIAL);
        //intent.setData(Uri.parse("tel:" + "36470083"));
        //startActivity(intent);

        //Usando o StartActivityForResult
        //Uri uri = Uri.parse("content://com.android.contacts/contacts");
        //startActivityForResult(new Intent(Intent.ACTION_PICK, uri), CODE);

        //Botões
        btnAbrirFone = findViewById(R.id.btnAbrirFone);
        btnTirarFoto = findViewById(R.id.btnTirarFoto);
        btnSite = findViewById(R.id.btnSite);
        btnLocalizacao = findViewById(R.id.btnLocalizacao);
        btnEmail = findViewById(R.id.btnEmail);
        btnAddAlarme = findViewById(R.id.btnAddAlarme);

        imagem = findViewById(R.id.imagem);

        //Eventos
        btnAbrirFone.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + "36470565"));

                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });

        btnTirarFoto.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                }
            }
        });

        btnSite.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                Uri uri = Uri.parse("http://www.google.com");
                intent.setData(uri);

                startActivity(intent);
            }
        });

        btnLocalizacao.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                Uri uri = Uri.parse("geo: -1.441101, -48.489362");
                intent.setData(uri);

                startActivity(intent);
            }
        });

        btnEmail.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:"));
                String enderecos[] = new String[]{"luis.bilecki@gmail.com"};
                intent.putExtra(Intent.EXTRA_EMAIL, enderecos);
                intent.putExtra(Intent.EXTRA_SUBJECT, "Teste direto do Android");

                startActivity(intent);
            }
        });

        btnAddAlarme.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM)
                        .putExtra(AlarmClock.EXTRA_MESSAGE, "Alarme personalizado")
                        .putExtra(AlarmClock.EXTRA_HOUR, 8)
                        .putExtra(AlarmClock.EXTRA_MINUTES, 0);

                startActivity(intent);
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CODE) {
            if (resultCode == Activity.RESULT_OK) {
                Uri uri = data.getData();
                Log.d("teste", "Contato URI: "+uri);

            } else if (resultCode == Activity.RESULT_CANCELED) {
                Log.d("teste","Cancelada.");
            }
        }

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imagem.setImageBitmap(imageBitmap);
        }
    }
}
