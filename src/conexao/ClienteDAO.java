package conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Cliente;

/**
 *
 * @author Gustavo
 */
public class ClienteDAO {
    private Conexao conexao;
    private Connection conn;

    public ClienteDAO(){
        this.conexao = new Conexao();
        this.conn = this.conexao.getConexao();
    }
    
    public void inserir(Cliente cliente) {
        String sql = "INSERT INTO Cliente (nome, endereco, email, telefone) VALUES (?,?,?,?)";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getEndereco());
            stmt.setString(3, cliente.getEmail());
            stmt.setString(4, cliente.getTelefone());
            stmt.execute();
        } catch (SQLException ex) {
            System.out.println("Erro ao inserir cliente: " + ex.getMessage());
        }
    }
    
    public List<Cliente> listar() {
        String sql = "SELECT * FROM Cliente";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
            ResultSet.CONCUR_UPDATABLE);
            
            ResultSet rs = stmt.executeQuery();
            List<Cliente> listaClientes = new ArrayList();
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setId_cliente(rs.getInt("id_cliente"));
                cliente.setNome(rs.getString("nome"));
                cliente.setEndereco(rs.getString("endereco"));
                cliente.setEmail(rs.getString("email"));
                cliente.setTelefone(rs.getString("telefone"));
                listaClientes.add(cliente);
            }
            return listaClientes;
        } catch (SQLException ex) {
            System.out.println("Erro ao listar clientes: " + ex.getMessage());
            return null;
        }
    }
}
