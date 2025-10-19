import java.util.List;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ConexaoDB verConexao = new ConexaoMysql(); 
      
        RepositorioDeProdutosMysql repositorio = new RepositorioDeProdutosMysql(gerenciadorConexao);

        System.out.println("--- VENDO OS PRODUTOS ---");

        try {
            ListaProduto listaDeProdutos = repositorio.listarProdutos();
            
            System.out.println("Lista de Produtos (Total: " + listaDeProdutos.size() + "):");

            if (listaDeProdutos.isEmpty()) {
                System.out.println("O banco de dados retornou a lista vazia. Verifique a tabela 'produtos'.");
            } else {
                int count = 0;
                for (Produto p : listaDeProdutos) {
                    if (count < 5) {
                        System.out.println(p);
                        count++;
                    } else {
                        break;
                    }
                }
            }

        } catch (Exception e) {
            System.err.println("\n*** ERRO NA APLICAÇÃO ***");
            System.err.println("Causa: " + e.getMessage());
            System.err.println("Verifique  se a tabela produtos existe.");
        }
    }
}
