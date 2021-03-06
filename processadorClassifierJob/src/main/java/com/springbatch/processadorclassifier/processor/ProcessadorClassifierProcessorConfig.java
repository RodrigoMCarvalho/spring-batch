package com.springbatch.processadorclassifier.processor;

import com.springbatch.processadorclassifier.dominio.Cliente;
import com.springbatch.processadorclassifier.reader.ClienteTransacaoLineMapperConfig;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.support.builder.ClassifierCompositeItemProcessorBuilder;
import org.springframework.classify.Classifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProcessadorClassifierProcessorConfig {
	@SuppressWarnings("rawtypes")
	@Bean
	public ItemProcessor processadorClassifierProcessor() {

		return new ClassifierCompositeItemProcessorBuilder<>()
				.classifier(classifier())
				.build();
	}

	private Classifier classifier() {
		return (Classifier<Object, ItemProcessor>)
				obj -> obj instanceof Cliente ? new ClienteProcessor() : new TransacaoProcessor();
	}
}
