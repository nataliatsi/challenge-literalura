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
                    1 - Buscar livro pelo título
                    2 - Listar livros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos em um determinado ano
                    5 - Listar livros em determinado idioma
                    
                    0 - Sair                              \s
                    """;

            System.out.println(menu);
            opc = scanner.nextInt();
            scanner.nextLine();

            switch (opc) {
                case 1:
                    buscarLivroPorTitulo();
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

    private void buscarLivroPorTitulo() {
        System.out.print("Digite o título do livro: ");
        String titulo = scanner.nextLine();
        System.out.println(" ");

        String urlComTitulo = ENDERECO + "?search=" + titulo.replace(" ", "%20");
        var json = consumo.obterDados(urlComTitulo);

        if (json == null || json.isEmpty()) {
            System.out.println("Nenhum dado retornado da API.");
            return;
        }

        Response resposta = conversor.obterDados(json, Response.class);

        if (resposta != null && !resposta.livros().isEmpty()) {
            resposta.livros().forEach(livro -> {
                System.out.println("Título: " + livro.titulo());
                livro.autores().forEach(autor -> System.out.println("Autor: " + autor.nome() + " " + (autor.anoNascimento() != null ? autor.anoNascimento() : " Ano de Nascimento Desconhecido")+ "  - " + (autor.anoFalecimento() != null ? autor.anoFalecimento() : "Ano de Falecimento Desconhecido")));

                var idiomasEnum = livro.getIdiomasAsEnum();
                System.out.println("Idioma(s): ");
                idiomasEnum.forEach(idioma -> System.out.println("- " + idioma));

                System.out.println("---------");
            });
        } else {
            System.out.println("Nenhum livro encontrado com o título fornecido.");
        }
    }
}
