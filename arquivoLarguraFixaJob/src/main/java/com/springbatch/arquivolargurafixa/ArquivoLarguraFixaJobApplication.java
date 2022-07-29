package com.springbatch.arquivolargurafixa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ArquivoLarguraFixaJobApplication {

	//Rodar com o parâmetro "arquivoClientes=file:files/clientes.txt" na IDE
	//arquivoClientes=file:files/clientes.txt arquivoClientesSaida=file:files/clientesSaida.txt
	public static void main(String[] args) {
		SpringApplication.run(ArquivoLarguraFixaJobApplication.class, args);
	}

}
