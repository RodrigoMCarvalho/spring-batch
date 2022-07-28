package com.springbatch.processadorscript;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProcessadorScriptJobApplication {

	//VM options  -Dnashorn.args=scripting
	//arquivoClientes=file:files/clientes.txt
	public static void main(String[] args) {
		SpringApplication.run(ProcessadorScriptJobApplication.class, args);
	}

}
