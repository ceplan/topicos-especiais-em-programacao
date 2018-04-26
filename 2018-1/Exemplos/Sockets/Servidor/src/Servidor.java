import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Servidor {
	
    public static void main(String[] args) throws IOException {
    	// Abre servidor de socket na porta 12345
        ServerSocket servidor = new ServerSocket(12345);
        System.out.println("Porta 12345 aberta e escutando!");
        
        // Indica que o servidor fica rodando até interromper a execução
	    try
	    {
	    	while(true){
	    		// Recebe um novo cliente
	    		Socket cliente = servidor.accept();
	    		
	    		System.out.println("Um novo cliente se conectou: " +
	    		cliente.getInetAddress().getHostAddress());
	    		
	    		// Faz a leitura do que o cliente enviou
	    		Scanner s = new Scanner(cliente.getInputStream());
	    		
	            while (s.hasNextLine()) {
	                System.out.println(s.nextLine());
	            }
	            
	            // Fecha a conexão
	            cliente.close();
	        }
	    }
	    finally
	    {
	    	servidor.close();
	    }
    }
}
