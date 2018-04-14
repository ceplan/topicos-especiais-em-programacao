import java.sql.*;

/*
 * O código abaixo foi construído com base em:
 * https://pt.stackoverflow.com/questions/27269/jdbc-sqlite-java
 */

public class Conexao {	 
	private Connection connection;  
	public Statement statement;  
	public ResultSet resultset;  
	
	public boolean conecta(){
		boolean result = true;
		
		try{
			// Carregar o driver do banco
			Class.forName("org.sqlite.JDBC");
			
			connection = DriverManager.getConnection("jdbc:sqlite:test.db");  
			statement = connection.createStatement();
			System.out.println("Conectou ao banco!");   
		}
		catch(Exception e){
			System.out.println("Erro ao conectar no banco: " + e.getMessage());  
			result = false;
		}
		
		return result;
	}  
	
	public void execQuery(String sql) throws Exception {  
		resultset = statement.executeQuery(sql);  
	}
	
	public boolean exec(String sql) {
		try{
			statement.execute(sql);
			System.out.println("Executou o código SQL!");
			return true;
		}
		catch(Exception e){
			System.out.println("Erro ao executar o SQL: " + e.getMessage()); 
			return false;
		}
	}
	
	public boolean desconecta()  
    {  
		boolean result = true;  
		 
		try  
		{  
		  connection.close();  
		  System.out.println("Desconectou o banco!");
		}  
		catch(SQLException e)  
		{  
		  System.out.println("Erro ao desconectar: " + e.getMessage());
		  result = false;  
		}
		 
		return result;
	}  

	public static void main(String args[]){
		Conexao con = new Conexao();
		con.conecta();
		con.exec("CREATE TABLE teste(id int, nome varchar(40));");
		con.desconecta();
	}	

}
