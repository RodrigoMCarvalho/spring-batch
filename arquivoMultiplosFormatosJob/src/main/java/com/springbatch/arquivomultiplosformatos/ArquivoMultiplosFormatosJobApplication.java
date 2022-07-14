package com.springbatch.arquivomultiplosformatos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ArquivoMultiplosFormatosJobApplication {

	//Rodar com o parâmetro "arquivoClientes=file:files/clientes.txt" na IDE
	public static void main(String[] args) {
		SpringApplication.run(ArquivoMultiplosFormatosJobApplication.class, args);
	}

}
