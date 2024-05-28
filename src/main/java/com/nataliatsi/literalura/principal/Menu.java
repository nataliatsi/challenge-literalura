package com.nataliatsi.literalura.principal;

import com.nataliatsi.literalura.model.DadosLivro;
import com.nataliatsi.literalura.service.ConsumoApi;
import com.nataliatsi.literalura.service.ConverteDados;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private Scanner scanner = new Scanner(System.in);
    private ConsumoApi consumo = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();

    private final String ENDERECO = "https://gutendex.com/books";
    private List<DadosLivro> dadosLivros = new ArrayList<>();

//    public Menu(LivrosRepository repository) {
//    }

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

    private DadosLivro getDadosLivro() {
        //System.out.println("Digite o nome do livro: ");
        System.out.println("Buscando por Frankenstien");
        var livroNome = "frankenstein";
       var json = consumo.obterDados(ENDERECO + "?search=" + livroNome.replace(" ", "%20"));

        DadosLivro dados = conversor.obterDados(json, DadosLivro.class);
        return dados;
    }


    private void buscarLivroWeb() {
        DadosLivro dados = getDadosLivro();
        dadosLivros.add(dados);
        System.out.println(dados);
    }
}