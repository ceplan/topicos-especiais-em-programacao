package udesc.br.tep.restehttpservice.service;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import udesc.br.tep.restehttpservice.pojo.CEP;

/**
 * Baseado no código presente em:
 * https://tableless.com.br/consumindo_web_service_no_android/
 */

/*
* Usamos AsyncTask aqui para rodar a requisição em Background.
* Assim o usuário não precisa esperar o retorno e a aplicação ficar travada.
*
* Primeiro Void = Parâmetro enviado para a execução da classe;
* Segundo Void = Segundo parâmetro recebido no método onProgressUdate, ou seja, quando a requisição
* é atualizada podemos atualizar uma barra de progresso.
* Terceiro Parâmetro = retorno da classe, no nosso caso um POJO
 */
public class HttpService extends AsyncTask<Void, Void, CEP> {
    private final String cep;

    public HttpService(String cep) {
        this.cep = cep;
    }

    @Override
    protected CEP doInBackground(Void... voids) {
        if (cep != null && cep.length() == 8) {
            try {
                URL url = new URL("https://viacep.com.br/ws/"+cep+"/json/");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                // Definir os parâmetros e configurações da conexão
                connection.setRequestMethod("GET");
                connection.setRequestProperty("Content-type", "application/json");
                connection.setRequestProperty("Accept", "application/json");
                connection.setDoOutput(true);
                connection.setConnectTimeout(5000);

                // Conectar
                connection.connect();

                // Ler
                Scanner scanner = new Scanner(url.openStream());
                StringBuilder retorno = new StringBuilder();

                // Jogamos o resultado em uma string
                while (scanner.hasNext()) {
                    retorno.append(scanner.next());
                }

                // Até aqui temos uma String que é um JSON
                // Como ler um JSON?
                // Biblioteca GSON da Google - adicionar no build.gradle

                // Faz o mapeamento da string json para um objeto do tipo CEP
                // Notem que devemos ter os mesmos atributos na classe e no retorno
                return new Gson().fromJson(retorno.toString(), CEP.class);
            } catch (MalformedURLException e) {
                Log.e("ERRO", "Erro na URL: "+e.getMessage());
            } catch (IOException e) {
                Log.e("ERRO", "Erro ao obter os dados da URL");
            }
        }

        return null;
    }
}
