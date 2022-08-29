package reader;

import dominio.DadosBancarios;
import dominio.Pessoa;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ArquivoDadosBancariosReaderConfig {

    @Bean
    public FlatFileItemReader<DadosBancarios> arquivoDadosBancariosReader() {
        return new FlatFileItemReaderBuilder<DadosBancarios>()
                .name("arquivoDadosBancariosReader")
                .resource(new FileSystemResource("files/dados_bancarios.csv"))
                .delimited()
                .names("pessoaId", "agencia", "conta", "banco", "id")
                .addComment("--") //irá ignorar os comentários no arquivo
                .targetType(DadosBancarios.class)
                .build();
    }

}
