package conexao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    
    public void inserir(NotaSaida ns) {
        String sql = "INSERT INTO NotaSaida (id_cliente, valorVenda, data_venda) VALUES (?,?,?)";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, ns.getId_cliente());
            stmt.setDouble(2, ns.getValorVenda());
            stmt.setDate(3, Date.valueOf(ns.getData_venda()));
            stmt.execute();
        } catch (SQLException ex) {
            System.out.println("Erro ao inserir nota: " + ex.getMessage());
        }
    }
    
    public NotaSaida getNota(int id) {
        String sql = "SELECT * FROM NotaSaida WHERE id_nota=?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.first()) {
                NotaSaida notasaida = new NotaSaida();
                notasaida.setId_nota(rs.getInt("id_nota"));
                notasaida.setId_cliente(rs.getInt("id_cliente"));
                notasaida.setValorVenda(rs.getDouble("valorVenda"));
                notasaida.setData_venda(rs.getDate("data_venda").toLocalDate());
                return notasaida;
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao consultar nota: " + ex.getMessage());
        }
        return null;
    }
    
    
    
}
