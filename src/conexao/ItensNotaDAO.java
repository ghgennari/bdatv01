package conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.ItensNota;

/**
 *
 * @author Gustavo
 */
public class ItensNotaDAO {
    private Conexao conexao;
    private Connection conn;

    public ItensNotaDAO() {
        this.conexao = new Conexao();
        this.conn = this.conexao.getConexao();
    }
    
    public void inserir(ItensNota itensnota) {
        String sql = "INSERT INTO ItensNotas (id_nota, id_produto, qtdVenda) VALUES (?,?,?)";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, itensnota.getId_nota());
            stmt.setInt(2, itensnota.getId_produto());
            stmt.setInt(3, itensnota.getQtdVenda());
            stmt.execute();
        } catch (SQLException ex) {
            System.out.println("Erro ao inserir item de nota: " + ex.getMessage());
        }
    }
    public List<ItensNota> listarPorNota(int idNota) {
        List<ItensNota> lista = new ArrayList<>();
        String sql = "SELECT * FROM ItensNotas WHERE id_nota=?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idNota);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                ItensNota itensnota = new ItensNota();
                itensnota.setId_nota(rs.getInt("id_nota"));
                itensnota.setId_produto(rs.getInt("id_produto"));
                itensnota.setQtdVenda(rs.getInt("qtdVenda"));
                lista.add(itensnota);
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao listar itens da nota: " + ex.getMessage());
        }
        return lista;
    }
    
    public List<ItensNota> buscarPorNota(int idNota) throws SQLException {
    List<ItensNota> lista = new ArrayList<>();
    String sql = "SELECT i.id_produto, p.nome as nomeProduto, i.qtdVenda " +
                 "FROM ItensNotas i " +
                 "JOIN Produto p ON i.id_produto = p.id_produto " +
                 "WHERE i.id_nota = ?";
    
    try (Connection con = Conexao.getConexao();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setInt(1, idNota);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            ItensNota item = new ItensNota();
            item.setId_produto(rs.getInt("id_produto"));
            item.setNomeProduto(rs.getString("nomeProduto")); // você precisa ter esse campo na classe
            item.setQtdVenda(rs.getInt("qtdVenda"));
            lista.add(item);
        }
    }

    return lista;
}
}
