package conexao;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {
     public Connection getConexao(){
        try{
            Connection conn = DriverManager.getConnection("jbdc:mysql://localhost:3306/bdatv01?useTimezone=true&serverTimezone=UTC",
                    "root", "");
            System.out.println("Conexão realizada com sucesso!");
            return conn;
        }
        catch (Exception e){
            System.out.println("Erro ao conectar no BD" + e.getMessage());
            return null;
        }
    }

}
