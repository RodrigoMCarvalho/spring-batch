package com.springbatch.processadorclassifier;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProcessadorClassifierJobApplication {

	//arquivoClientes=file:files/clientes.txt
	public static void main(String[] args) {
		SpringApplication.run(ProcessadorClassifierJobApplication.class, args);
	}

}
