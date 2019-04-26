import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

public class Cliente {
	public static void main(String args[]) throws IOException{
		// Dados de conexão
		String ip = "127.0.0.1";
		int porta = 12345;
		
		// Faz a conexão
		System.out.println("Conectando em " + ip + ":" + porta);
		Socket cliente = new Socket(ip, porta);
		
		// Envia os dados
		PrintStream saida = new PrintStream(cliente.getOutputStream());
		saida.println("Uma mensagem");
		
		// Encerra
		System.out.println("Fechando a conexão...");
		cliente.close();
	}
}
