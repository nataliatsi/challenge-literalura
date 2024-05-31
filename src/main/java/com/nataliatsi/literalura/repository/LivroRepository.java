package com.nataliatsi.literalura.repository;

import com.nataliatsi.literalura.model.Idioma;
import com.nataliatsi.literalura.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {
    Optional<Livro> findByTitulo(String nomeLivro);
    List<Livro> findByIdiomaEnum(Idioma idiomaEnum);
}
