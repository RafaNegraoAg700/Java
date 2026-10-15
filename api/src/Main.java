import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

public class Main{

    public static void main(String[] args) throws IOException{

        HttpServer servidor = HttpServer.create(
                new InetSocketAddress(8000),0
        );

        servidor.createContext("/", exchange ->{
            String caminho = exchange.getRequestURI().getPath();
            String  resposta;
            int status = 200;

            if (caminho.equals("/")){
                resposta = "{\"mensagem\":\"API funcionando!\"}";

            } else if (caminho.equals("/tarefas")){
                resposta = "["
                        + "{\"id\":1,\"nome\":\"Estudar Java\"},"
                        + "{\"id\":2,\"nome\":\"Criar uma API\"}"
                        + "]";
            }else {
                resposta = "{\"erro\":\"Rota nao encontrada\"}";
                status = 404;
            }

            exchange.getResponseHeaders().set(
                    "Content-Type", "application/json; charset=UTF-8"
            );

            byte[] dados = resposta.getBytes("UTF-8");
            exchange.sendResponseHeaders(status, dados.length);

            try (OutputStream saida = exchange.getResponseBody()){
                saida.write(dados);
            }
        });
        servidor.start();
        System.out.println("API rodando em http://localhost:8000");
        System.out.println("API rodando em http://localhost:8000/tarefas");

    }

}
