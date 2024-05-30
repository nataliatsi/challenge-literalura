package com.nataliatsi.literalura.service;

import com.nataliatsi.literalura.model.Livro;
import com.nataliatsi.literalura.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    public List<Livro> findAllLivros(){
        return livroRepository.findAll();
    }
}
