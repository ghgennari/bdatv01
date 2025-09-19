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
    
    public Produto getProduto(int id){
        String sql = "SELECT * FROM Produto WHERE id_produto = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.first()) {
                Produto produto = new Produto();
                produto.setId_produto(rs.getInt("id_produto"));
                produto.setNome(rs.getString("nome"));
                produto.setDescricao(rs.getString("descricao"));
                produto.setPreco(rs.getDouble("preco"));
                produto.setQtd_estoque(rs.getInt("qtd_estoque"));
                return produto;
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao consultar produto: " + ex.getMessage());
        }
        return null;
    }
    
    public void editar(Produto produto) {
        String sql = "UPDATE Produto SET nome=?, descricao=?, preco=?, qtd_estoque=? WHERE id_produto=?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, produto.getNome());
            stmt.setString(2, produto.getDescricao());
            stmt.setDouble(3, produto.getPreco());
            stmt.setInt(4, produto.getQtd_estoque());
            stmt.setInt(5, produto.getId_produto());
            stmt.execute();
        } catch (SQLException ex) {
            System.out.println("Erro ao atualizar produto: " + ex.getMessage());
        }
    }
    
    public void excluir(int id) {
        String sql = "DELETE FROM Produto WHERE id_produto=?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.execute();
        } catch (SQLException ex) {
            System.out.println("Erro ao excluir produto: " + ex.getMessage());
        }
    }
    
     public List<Produto> listar() {
        List<Produto> lista = new ArrayList<>();
        String sql = "SELECT * FROM Produto";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Produto produto = new Produto();
                produto.setId_produto(rs.getInt("id_produto"));
                produto.setNome(rs.getString("nome"));
                produto.setDescricao(rs.getString("descricao"));
                produto.setPreco(rs.getDouble("preco"));
                produto.setQtd_estoque(rs.getInt("qtd_estoque"));
                lista.add(produto);
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao listar produtos: " + ex.getMessage());
        }
        return lista;
    }
    
}
