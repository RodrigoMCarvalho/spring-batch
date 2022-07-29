package com.springbatch.arquivodelimitado.writer;

import com.springbatch.arquivodelimitado.dominio.Cliente;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
public class LeituraArquivoDelimitadoWriterConfig {

//	@Bean
//	public ItemWriter<Cliente> leituraArquivoDelimitadoWriter() {
//		return items -> items.forEach(System.out::println);
//	}

	@StepScope //Para obter dados do jobParameters
	@Bean
	public FlatFileItemWriter<Cliente> escritaArquivoDelimitadoWriter(
			@Value("#{jobParameters['arquivoClientesSaida']}") Resource arquivoClientesSaida) {

		return new FlatFileItemWriterBuilder<Cliente>()
				.name("escritaArquivoDelimitadoWriter")
				.resource(arquivoClientesSaida)
				.delimited()
				.delimiter(";")   //substitui a virgula do arquivo de entrada por ponto e virgula
				.names("nome","sobrenome","idade","email")
				.build();
	}


}
