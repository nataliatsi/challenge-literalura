package com.nataliatsi.literalura.principal;

import com.nataliatsi.literalura.model.Response;
import com.nataliatsi.literalura.service.ConsumoApi;
import com.nataliatsi.literalura.service.ConverteDados;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {

    private final Scanner scanner = new Scanner(System.in);
    private final ConsumoApi consumo = new ConsumoApi();
    private final ConverteDados conversor = new ConverteDados();
    private final String ENDERECO = "https://gutendex.com/books";
    private final List<Response> dadosLivros = new ArrayList<>();

    public void exibeMenu() {
        int opc = -1;
        while (opc != 0) {
            var menu = """
                    1 - Buscar livro pos(1)
                    x - Buscar livro pelo título
                    x - Listar livros registrados
                    x - Listar autores registrados
                    x - Listar autores vivos em um determinado ano
                    x - Listar livros em determinado idioma
                    
                    0 - Sair                              \s
                    """;

            System.out.println(menu);
            opc = scanner.nextInt();
            scanner.nextLine();

            switch (opc) {
                case 1:
                    buscarLivroWeb();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        }
    }

    private void buscarLivroWeb() {
        Response dados = getDadosLivro();

        if (dados != null) {
            dadosLivros.add(dados);
            var livro = dados.livros().get(1);
            var idiomas = livro.idiomas();
            var autores = livro.autores();

            System.out.println("Livro na posição 1");
            System.out.println("Título: " + livro.titulo());
            autores.forEach(autor -> System.out.println(autor.nome() + ", "));
            System.out.println("Disponível no(s) idioma(s): ");
            for (String idioma : idiomas) {
                System.out.println("- " + idioma);
            }
        } else {
            System.out.println("No data found.");
        }
    }


    private Response getDadosLivro(){
        var json = consumo.obterDados(ENDERECO);

        if (json == null || json.isEmpty()) {
            System.out.println("No data returned from the API.");
            return null;
        }

        return conversor.obterDados(json, Response.class);
    }
}
