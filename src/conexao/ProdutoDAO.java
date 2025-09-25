package conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Produto;

/**
 *
 * @author Gustavo
 */
public class ProdutoDAO {
    private Conexao conexao;
    private Connection conn;
    
    public ProdutoDAO(){
        this.conexao = new Conexao();
        this.conn = this.conexao.getConexao();
    }
    
    public void inserir(Produto produto){
        String sql = "INSERT INTO Produto(nome, descricao, preco, qtd_estoque) VALUES (?,?,?,?)";
        try{
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, produto.getNome());
            stmt.setString(2, produto.getDescricao());
            stmt.setDouble(3, produto.getPreco());
            stmt.setInt(4, produto.getQtd_estoque());
            stmt.execute();
        }catch (SQLException ex) {
            System.out.println("Erro ao inserir produto: " + ex.getMessage());
        }
    }
    
     public List<Produto> listar() {
        String sql = "SELECT * FROM Produto";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            
            ResultSet rs = stmt.executeQuery();
            List<Produto> listaProdutos = new ArrayList();
            while (rs.next()) {
                Produto produto = new Produto();
                produto.setId_produto(rs.getInt("id_produto"));
                produto.setNome(rs.getString("nome"));
                produto.setDescricao(rs.getString("descricao"));
                produto.setPreco(rs.getDouble("preco"));
                produto.setQtd_estoque(rs.getInt("qtd_estoque"));
                listaProdutos.add(produto);
            }
            return listaProdutos;
        } catch (SQLException ex) {
            System.out.println("Erro ao listar produtos: " + ex.getMessage());
            return null;
        }
}
}