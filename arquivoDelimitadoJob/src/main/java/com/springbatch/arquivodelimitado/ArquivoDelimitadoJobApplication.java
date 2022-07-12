package com.springbatch.arquivodelimitado;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ArquivoDelimitadoJobApplication {

	//Rodar com o parâmetro "arquivoClientes=file:files/clientes.txt" na IDE
	public static void main(String[] args) {
		SpringApplication.run(ArquivoDelimitadoJobApplication.class, args);
	}

}
