package com.springbatch.processadorvalidacao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProcessadorValidacaoJobApplication {

	//arquivoClientes=file:files/clientes.txt
	public static void main(String[] args) {
		SpringApplication.run(ProcessadorValidacaoJobApplication.class, args);
	}

}
