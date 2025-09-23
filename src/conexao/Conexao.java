package conexao;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Gustavo
 */
public class Conexao {
    public static Connection getConexao(){
        try{
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/bdatv01?useTimezone=true&serverTimezone=UTC",
                    "root","");
            System.out.println("Conexão realiada");
            return conn;
        }
        catch(Exception e){
            System.out.println("Erro ao conectar"+e.getMessage());
            return null;
        }
    }
}
