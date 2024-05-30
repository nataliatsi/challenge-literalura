package com.nataliatsi.literalura.service;

import com.nataliatsi.literalura.model.Autor;
import com.nataliatsi.literalura.model.AutorDTO;
import com.nataliatsi.literalura.model.Livro;
import com.nataliatsi.literalura.model.LivroDTO;
import com.nataliatsi.literalura.repository.AutorRepository;
import com.nataliatsi.literalura.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

//    public void salvarLivro(Livro livro) {
//        livroRepository.save(livro);
//    }

//    public void salvarLivro(LivroDTO livroDTO) {
//        List<Autor> autores = livroDTO.autores().stream()
//                .map(this::converterAutorDTO)
//                .collect(Collectors.toList());
//
//        autores.forEach(autorRepository::save);
//
//        Livro livro = new Livro();
//        livro.setTitulo(livroDTO.titulo());
//        livro.setAutorList(autores);
//        livro.setIdiomasEnum(livroDTO.getIdiomasAsEnum());
//
//        livroRepository.save(livro);
//    }


    public Optional<Livro> buscarPorTitulo(String nomeLivro){
        return livroRepository.findByTitulo(nomeLivro);
    }

    public List<Livro> listarLivrosRegistrados(){
        return livroRepository.findAll();
    }

    public List<Autor> listarAutoresRegistrados(){
        return autorRepository.findAll();
    }

//    public List<Livro> listarLivrosPorIdioma(String idioma){
//        return livroRepository.findByIdioma(idioma);
//    }

    public List<Autor> listarAutoresVivosEmDeterminadoAno(int ano){
        return autorRepository.autoresVivos(ano);
    }

}
