package com.nataliatsi.literalura;

import com.nataliatsi.literalura.principal.Principal;
import com.nataliatsi.literalura.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {

      @Autowired
      private  LivroService livroService;

    public static void main(String[] args) {
        SpringApplication.run(LiteraluraApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Principal p = new Principal(livroService);
        p.exibeMenu();
    }
}
