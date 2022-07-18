package com.springbatch.arquivomultiplosformatos.reader;

import com.springbatch.arquivomultiplosformatos.dominio.Cliente;
import com.springbatch.arquivomultiplosformatos.dominio.Transacao;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.mapping.PatternMatchingCompositeLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.LineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ClienteTransacaoLineMapper {

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Bean
    public PatternMatchingCompositeLineMapper lineMapper() {
        PatternMatchingCompositeLineMapper lineMapper = new PatternMatchingCompositeLineMapper();
        lineMapper.setTokenizers(tokenizers());
        lineMapper.setFieldSetMappers(fieldSetMappers());
        return lineMapper;
    }

    private Map<String, LineTokenizer> tokenizers() {
        Map<String, LineTokenizer> tokenizers = new HashMap<>();
        tokenizers.put("0*", clienteLineTokenizer()); //Toda linha começada por 0 e qualquer coisa, é um cliente
        tokenizers.put("1*", transacaoLineTokenizer()); //Toda linha começada por 1 e qualquer coisa, é uma transação
        return tokenizers;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    private Map<String, FieldSetMapper> fieldSetMappers() {
        Map<String, FieldSetMapper> fieldSetMappers = new HashMap<>();
        fieldSetMappers.put("0*", fieldSetMapper(Cliente.class)); //Toda linha começada por 0 e qualquer coisa, é um cliente
        fieldSetMappers.put("1*",  fieldSetMapper(Transacao.class)); //Toda linha começada por 1 e qualquer coisa, é uma transação
        return fieldSetMappers;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    private FieldSetMapper fieldSetMapper(Class classe) {
        BeanWrapperFieldSetMapper fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(classe);
        return fieldSetMapper;
    }

    private LineTokenizer clienteLineTokenizer() {
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setNames("nome", "sobrenome", "idade", "email");
        lineTokenizer.setIncludedFields(1,2,3,4); //Ignora o campo 0, pois é o identifcador (0 ou 1 no arquivo clientes.txt)
        return lineTokenizer;
    }

    private LineTokenizer transacaoLineTokenizer() {
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setNames("id", "descricao", "valor");
        lineTokenizer.setIncludedFields(1,2,3); //Ignora o campo 0, pois é o identifcador (0 ou 1 no arquivo clientes.txt)
        return lineTokenizer;
    }
}
