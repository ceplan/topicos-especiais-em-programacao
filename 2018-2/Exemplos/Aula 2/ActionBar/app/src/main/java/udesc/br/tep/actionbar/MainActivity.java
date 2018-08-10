package udesc.br.tep.actionbar;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Minha barra de ações");
        actionBar.setSubtitle("Meu subtítulo");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_mainactivity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                Toast.makeText(this, "Ação search foi selecionada!", Toast.LENGTH_SHORT)
                        .show();
                break;
            case R.id.action_settings:
                Toast.makeText(this, "Ação settings foi selecionada!", Toast.LENGTH_SHORT)
                        .show();
                break;
            case R.id.action_refresh:
                Toast.makeText(this, "Ação refresh foi selecionada!", Toast.LENGTH_SHORT)
                        .show();
            default:
                break;
        }

        return true;
    }
}
