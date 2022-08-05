package com.springbatch.demonstrativoorcamentario.writer;

import com.springbatch.demonstrativoorcamentario.dominio.GrupoLancamento;
import com.springbatch.demonstrativoorcamentario.dominio.Lancamento;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.*;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.builder.MultiResourceItemReaderBuilder;
import org.springframework.batch.item.file.builder.MultiResourceItemWriterBuilder;
import org.springframework.batch.item.file.transform.LineAggregator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Configuration
public class DemonstrativoOrcamentarioWriterConfig {

	@StepScope
	@Bean
	public MultiResourceItemWriter<GrupoLancamento> multiDemonstrativoOrcamentarioWriter(
			@Value("#{jobParameters['demonstrativosOrcamentarios']}") Resource demonstrativosOrcamentarios,
			FlatFileItemWriter<GrupoLancamento> demonstrativoOrcamentarioWriter
	) {
		return new MultiResourceItemWriterBuilder<GrupoLancamento>()
				.name("multiDemonstrativoOrcamentarioWriter")
				.resource(demonstrativosOrcamentarios)
				.delegate(demonstrativoOrcamentarioWriter)
				.resourceSuffixCreator(suffixCreator())
				.itemCountLimitPerResource(1)   //a cada grupoLancamento ele cria um arquivo. Ajustado o tamanho do chunck para 1
				.build();
	}

	private ResourceSuffixCreator suffixCreator() {
		return index -> index + ".txt";
	}

	@StepScope //Para obter dados do jobParameters
	@Bean
	public FlatFileItemWriter<GrupoLancamento> demonstrativoOrcamentarioWriter(
			@Value("#{jobParameters['demonstrativoOrcamentario']}") Resource demonstrativoOrcamentario,
			DemonstrativoOrcamentarioRodape demonstrativoOrcamentarioRodape
	) {
		FlatFileFooterCallback rodapeCallback;
		return new FlatFileItemWriterBuilder<GrupoLancamento>()
				.name("demonstrativoOrcamentario")
				.resource(demonstrativoOrcamentario)
				.lineAggregator(lineAggregator())
				.headerCallback(cabecalhoCallback())
				.footerCallback(demonstrativoOrcamentarioRodape)
				.build();
	}

	private FlatFileHeaderCallback cabecalhoCallback() {
		return writer -> {
			writer.append(String.format("SISTEMA INTEGRADO: XPTO \t\t\t\t DATA: %s\n", new SimpleDateFormat("dd/MM/yyyy").format(new Date())));
			writer.append(String.format("MÓDULO: ORÇAMENTO \t\t\t\t\t\t HORA: %s\n", new SimpleDateFormat("HH:MM").format(new Date())));
			writer.append(String.format("\t\t\t DEMONSTRATIVO ORCAMENTARIO\n"));
			writer.append(String.format("----------------------------------------------------------------------------\n"));
			writer.append(String.format("CODIGO NOME VALOR\n"));
			writer.append(String.format("\t Data Descricao Valor\n"));
			writer.append(String.format("----------------------------------------------------------------------------\n"));
		};
	}

	private LineAggregator<GrupoLancamento> lineAggregator() {
		return grupoLancamento -> {
			String formatGrupoLancamento = String.format("[%d] %s - %s\n", grupoLancamento.getCodigoNaturezaDespesa(),
					grupoLancamento.getDescricaoNaturezaDespesa(),
					NumberFormat.getCurrencyInstance().format(grupoLancamento.getTotal()));

			StringBuilder sb = new StringBuilder();
			for (Lancamento lancamento : grupoLancamento.getLancamentos()) {
				sb.append(String.format("\t [%s] %s - %s\n", new SimpleDateFormat("dd/MM/yyyy").format(lancamento.getData()), lancamento.getDescricao(),
						NumberFormat.getCurrencyInstance().format(lancamento.getValor())));

			}
			String formatLancamento = sb.toString();
			return formatGrupoLancamento + formatLancamento;
		};
	}
}
