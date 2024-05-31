package com.nataliatsi.literalura.service;

import com.nataliatsi.literalura.model.*;
import com.nataliatsi.literalura.repository.AutorRepository;
import com.nataliatsi.literalura.repository.LivroRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LivroService {

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private LivroRepository livroRepository;

    @Transactional
    public void salvarLivro(Response response) {
        List<LivroDTO> livrosDTO = response.livros();
        for (LivroDTO livroDTO : livrosDTO) {
            List<Autor> autores = livroDTO.autores().stream()
                    .map(this::converterAutorDTO)
                    .collect(Collectors.toList());

            try {

                autorRepository.saveAll(autores);

                Livro livro = new Livro();
                livro.setTitulo(livroDTO.titulo());
                livro.setAutorList(autores);
                if (!livroDTO.getIdiomasAsEnum().isEmpty()) {
                    livro.setIdiomaEnum(livroDTO.getIdiomasAsEnum().get(0));
                } else {
                    livro.setIdiomaEnum(Idioma.OTHER);
                }

                livroRepository.save(livro);

            } catch (DataAccessException e) {
                System.err.println("Erro ao acessar dados do banco: " + e.getMessage());
                e.printStackTrace();
                throw new RuntimeException("Erro ao salvar dados no banco.", e);

            } catch (Exception e) {
                System.err.println("Erro inesperado: " + e.getMessage());
                e.printStackTrace();
                throw new RuntimeException("Erro inesperado ao salvar livro.", e);
            }
        }
    }

    private Autor converterAutorDTO(AutorDTO autorDTO) {
        Autor autor = new Autor();
        autor.setNome(autorDTO.nome());
        autor.setAnoNascimento(autorDTO.anoNascimento());
        autor.setAnoFalecimento(autorDTO.anoFalecimento());
        return autor;
    }

    public Optional<Livro> buscarPorTitulo(String nomeLivro){
        return livroRepository.findByTitulo(nomeLivro);
    }

    public List<Livro> listarLivrosRegistrados(){
        return livroRepository.findAll();
    }

    public List<Autor> listarAutoresRegistrados(){
        return autorRepository.findAll();
    }

    public List<Livro> listarLivrosPorIdioma(Idioma idioma){
        return livroRepository.findByIdiomaEnum(idioma);
    }

    public List<Autor> listarAutoresVivosEmDeterminadoAno(int ano){
        return autorRepository.autoresVivos(ano);
    }

}
