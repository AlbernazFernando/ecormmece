import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class RepositorioDeProdutosMysql {

    private final ConexaoDB verConexao;

    public RepositorioDeProdutosMysql(ConexaoDB verConexao) {
        this.verConexao = verConexao;
    }

  
    public ListaProduto listarProdutos() throws Exception {
        Connection conexao = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
  
        ListaProduto listaDeProdutos = new ArrayList<>();

        String sql = "SELECT id, nome, preco FROM produtos";

        try {
            conexao = verConexao.obterConexao();

            stmt = conexao.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                double preco = rs.getDouble("preco");
   
                listaDeProdutos.add(new Produto(id, nome, preco));
            }

        } catch (SQLException e) {
            throw new Exception("Erro ao listar produtos no banco de dados: " + e.getMessage());
        } finally {
            if (rs != null) try { rs.close(); } catch (SQLException ignored) {}
            if (stmt != null) try { stmt.close(); } catch (SQLException ignored) {}
            verConexao.fecharConexao(conexao);
        }

        return listaDeProdutos;
    }
  
    public List<Produto> consultarProdutos(String termo) throws Exception {
        Connection conexao = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Produto> listaDeProdutosEncontrados = new ArrayList<>();

        String sql = "SELECT id, nome, preco FROM produtos WHERE nome LIKE ?";

        try {
            conexao = verConexao.obterConexao();

            stmt = conexao.prepareStatement(sql);
 
            stmt.setString(1, "%" + termo + "%"); 

            rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                double preco = rs.getDouble("preco");

                listaDeProdutosEncontrados.add(new Produto(id, nome, preco));
            }

        } catch (SQLException e) {
            throw new Exception("Erro ao consultar produtos no banco de dados: " + e.getMessage());
        } finally {
            if (rs != null) try { rs.close(); } catch (SQLException ignored) {}
            if (stmt != null) try { stmt.close(); } catch (SQLException ignored) {}
            gerenciadorConexao.fecharConexao(conexao);
        }

        return listaDeProdutosEncontrados;
    }
}
