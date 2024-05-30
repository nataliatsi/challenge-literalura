package com.nataliatsi.literalura.repository;

import com.nataliatsi.literalura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Integer> {

    Optional<Autor> findByNome(String nomeAutor);

    // DEVE RETORNAR UMA LISTA DE AUTORES VIVOS EM UM DETERMINADO ANO
    @Query("SELECT a FROM Autor a WHERE a.anoNascimento <= :ano AND a.anoFalecimento > :ano")
    List<Autor> autoresVivos(int ano);
}
