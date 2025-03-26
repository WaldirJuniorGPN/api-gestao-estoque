package br.com.gestao.gestao_estoque;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class GestaoEstoqueApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestaoEstoqueApplication.class, args);
	}

}
