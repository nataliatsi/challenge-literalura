package com.nataliatsi.literalura.principal;

import com.nataliatsi.literalura.model.Autor;
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
                case 2:
                    listarLivros();
                case 3:
                    listarAutores();
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida");
            }
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
            System.out.println("Idiomas: " + dados.getIdiomaEnum());
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
//
//    public void buscarLivroPorTitulo() {
//        System.out.println("Digite o título do livro:");
//        String titulo = scanner.nextLine();
//
//        Response response = getDadosLivro();
//
//        if (response != null && response.livros() != null) {
//            System.out.println("Livro encontrado e salvo com sucesso!");
////            response.livros().stream()
////                    .filter(livroDTO -> livroDTO.titulo().equalsIgnoreCase(titulo))
////                    .findFirst()
////                    .ifPresent(livroDTO -> {
////
////                        Livro livro = new Livro();
////
////                        livro.setTitulo(livroDTO.titulo());
////                        livro.setIdiomaEnum(livroDTO);
////                        livro.setAutorList(livroDTO.autores());
////
////                        livroService.salvarLivro(livro);
////
////                        System.out.println("Livro encontrado e salvo com sucesso!");
////                    });
//        } else {
//            System.out.println("Nenhum livro encontrado com o título fornecido.");
//        }
//    }


}
