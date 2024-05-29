package com.nataliatsi.literalura.principal;

import com.nataliatsi.literalura.model.Response;
import com.nataliatsi.literalura.service.ApiService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private Scanner scanner = new Scanner(System.in);
    private ApiService consumo = new ApiService();
    private final String ENDERECO = "https://gutendex.com/books";

    private List<Response> dadosLivros = new ArrayList<>();

    public Menu() {
    }

    public void exibeMenu() {
        int opc = -1;
        while (opc != 0) {
            var menu = """
                    1 - Buscar livro
                    0 - Sair                               
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

    private Response getDadosLivro() {
        System.out.println("Buscando dados de livros...");
        return consumo.obterDados(ENDERECO, Response.class);
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
}
