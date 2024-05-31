package com.nataliatsi.literalura.principal;

import com.nataliatsi.literalura.model.Autor;
import com.nataliatsi.literalura.model.Idioma;
import com.nataliatsi.literalura.model.Livro;
import com.nataliatsi.literalura.model.Response;
import com.nataliatsi.literalura.service.ConsumoApi;
import com.nataliatsi.literalura.service.ConverteDados;
import com.nataliatsi.literalura.service.LivroService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {

    private final Scanner scanner = new Scanner(System.in);
    private final ConsumoApi consumo = new ConsumoApi();
    private final ConverteDados conversor = new ConverteDados();

    private LivroService livroService;

    private final String ENDERECO = "https://gutendex.com/books";
    private final List<Response> dadosLivros = new ArrayList<>();


    public Principal() {
    }

    public Principal(LivroService livroService) {
        this.livroService = livroService;
    }


    public void exibeMenu() {
        int opc = -1;
        while (opc != 0) {
            var menu = """
                    1 - Cadastrar livro
                    2 - Buscar livro pelo título
                    3 - Listar livros registrados
                    4 - Listar autores registrados
                    5 - Listar autores vivos em um determinado ano
                    6 - Listar livros em determinado idioma
                    
                    0 - Sair                              \s
                    """;

            System.out.println(menu);
            opc = scanner.nextInt();
            scanner.nextLine();

            switch (opc) {
                case 1:
                    buscarESalvarLivrosDeAPI();
                    break;
                case 2:
                    buscarLivroPorTitulo();
                    break;
                case 3:
                    listarLivros();
                    break;
                case 4:
                    listarAutores();
                    break;
                case 5:
                    listarAutoresVivos();
                    break;
                case 6:
                    buscarLivrosPorIdioma();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        }
    }


    private Response getDadosLivro(String nomeLivro){
        var json = consumo.obterDados(ENDERECO + "?search=" + nomeLivro.replace(" ", "%20"));

        if (json == null || json.isEmpty()) {
            System.out.println("Nenhum dado retornado da API.");
            return null;
        }

        return conversor.obterDados(json, Response.class);
    }

    private void buscarESalvarLivrosDeAPI() {
        System.out.println("Buscar livros na API Gutendex e salvar no banco de dados");
        System.out.println("-------------");
        System.out.print("Digite o título do livro: ");
        String titulo = scanner.nextLine();

        Response response = getDadosLivro(titulo);
        if (response != null) {
            livroService.salvarLivro(response);
            System.out.println("Livro(s) salvo(s) com sucesso.");
        } else {
            System.out.println("Falha ao obter dados da API.");
        }
    }


    private void buscarLivroPorTitulo(){
        System.out.print("Digite o nome do livro: ");
        String titulo = scanner.nextLine();
        var livro = livroService.buscarPorTitulo(titulo);

        if(livro.isPresent()){
            var dados = livro.get();
            System.out.println("Título: " + dados.getTitulo());
            for(var autores : dados.getAutorList()){
                System.out.println("Autor: " + autores.getNome() + " " + autores.getAnoNascimento() + " - " + autores.getAnoFalecimento());
            }
            System.out.println("Idioma: " + dados.getIdiomaEnum());
            System.out.println("-------------");
        } else {
            System.out.println("Livro não encontrado");
        }
    }

    private void listarLivros(){
        List<Livro> livros = livroService.listarLivrosRegistrados();
        livros.forEach(System.out::println);
    }

    private void listarAutores(){
        List<Autor> autores = livroService.listarAutoresRegistrados();
        autores.forEach(System.out::println);
    }

    public void listarAutoresVivos(){
        System.out.println("Autor(es) vivo(s) em um determinado ano");
        System.out.print("Digite o ano: ");

        var ano = scanner.nextInt();
        scanner.nextLine();

        List<Autor> autores = livroService.listarAutoresVivosEmDeterminadoAno(ano);
        autores.forEach(System.out::println);
    }

    private void buscarLivrosPorIdioma() {
        System.out.print("Digite o código do idioma (ex: en, es, fr, pt ou other): ");
        String codigoIdioma = scanner.nextLine().trim();

        Idioma idioma;
        try {
            idioma = Idioma.fromString(codigoIdioma);
        } catch (IllegalArgumentException e) {
            System.out.println("Código de idioma inválido.");
            return;
        }

        List<Livro> livros = livroService.listarLivrosPorIdioma(idioma);

        if (livros.isEmpty()) {
            System.out.println("Nenhum livro encontrado para o idioma: " + idioma.getCode());
        } else {
            livros.forEach(System.out::println);
        }
    }

}