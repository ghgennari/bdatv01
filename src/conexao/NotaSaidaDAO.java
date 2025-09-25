package conexao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import model.NotaSaida;

/**
 *
 * @author Gustavo
 */
public class NotaSaidaDAO {
    private Conexao conexao;
    private Connection conn;

    public NotaSaidaDAO() {
        this.conexao = new Conexao();
        this.conn = this.conexao.getConexao();
    }
    
    public int inserir(NotaSaida ns) {
        String sql = "INSERT INTO NotaSaida (id_cliente, data_venda) VALUES (?,?)";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, ns.getId_cliente());
            stmt.setDate(2, Date.valueOf(ns.getData_venda()));
            stmt.executeUpdate();
             ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao inserir nota: " + ex.getMessage());
        }
        return -1;
    }
    
}
