package com.springbatch.arquivomultiplosformatos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ArquivoMultiplosFormatosJobApplication /*implements CommandLineRunner*/ {

//	@Autowired
//	private JobLauncher jobLauncher;
//
//	@Autowired
//	private JobExplorer jobExplorer;
//
//	@Autowired
//	private ApplicationContext appContext;



	//Rodar com o parâmetro "arquivoClientes=file:files/clientes.txt" na IDE
	public static void main(String[] args) {
		SpringApplication.run(ArquivoMultiplosFormatosJobApplication.class, args);
	}

//	@Override
//	public void run(String... args) throws Exception {
//		Job job = appContext.getBean("arquivoMultiplosFormatosJob", Job.class);
//		JobParameters params =
//				new JobParametersBuilder(jobExplorer).addString("arquivoClientes", "file:files/clientes.txt")
//						.getNextJobParameters(job)
//						.toJobParameters();
//		JobExecution execution = jobLauncher.run(job, params);
//		System.out.println("STATUS :: " + execution.getStatus());
//	}

}
