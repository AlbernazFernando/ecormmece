import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoMysql implements ConexaoDB {

    private static final String URL = "jdbc:mysql://localhost:3307/ecommerce";
    private static final String USUARIO = "root";
    private static final String SENHA = "catolica";

@Override
    public Connection obterConexao() throws Exception {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
            System.out.println("Conexao com o banco de dados estabelecida com sucesso");
            return conexao;

        } catch (ClassNotFoundException e) {
            throw new Exception("Driver JDBC do Mysql nao encontrado: " + e.getMessage());
        } catch (SQLException e) {
            throw new Exception("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
    }
    @Override

    public void fecharConexao (Connection conexao) {
    if (conexao != null) {
        try {
            conexao.close();
            System.out.println("Conexao com o banco de dados fechada com sucesso");

        } catch (SQLException e) {
            System.err.println("Erro ao fechar conexao com o banco de dados: " + e.getMessage());
        }
    }
    }
}
