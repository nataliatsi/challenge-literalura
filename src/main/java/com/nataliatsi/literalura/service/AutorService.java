package com.nataliatsi.literalura.service;

import com.nataliatsi.literalura.model.Autor;
import com.nataliatsi.literalura.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutorService {

    @Autowired
    private AutorRepository autorRepository;

    public List<Autor> findAllAutores() {
        return autorRepository.findAll();
    }
}
