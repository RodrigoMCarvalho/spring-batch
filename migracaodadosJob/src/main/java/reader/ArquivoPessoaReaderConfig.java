package reader;

import dominio.Pessoa;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ArquivoPessoaReaderConfig {

    @Bean
    public FlatFileItemReader<Pessoa> arquivoPessoaReader() {
        return new FlatFileItemReaderBuilder<Pessoa>()
                .name("arquivoPessoaReader")
                .resource(new FileSystemResource("files/pessoas.csv"))
                .delimited()
                .names("nome", "email", "dataNascimento", "idade", "id")
                .addComment("--") //irá ignorar os comentários no arquivo
                .targetType(Pessoa.class)
                .fieldSetMapper(fieldSetMapper())
                .build();
    }

    //Criado o mapper devido ao campo Date
    private FieldSetMapper<Pessoa> fieldSetMapper()  {
        return fieldSet -> {
            Pessoa pessoa = new Pessoa();
            pessoa.setNome(fieldSet.readString("nome"));
            pessoa.setEmail(fieldSet.readString("email"));
            pessoa.setDataNascimento(new Date(fieldSet.readDate("dataNascimento", "yyyy-MM-dd HH:mm:ss").getTime()));
            pessoa.setId(fieldSet.readLong("id"));
            pessoa.setIdade(fieldSet.readInt("idade"));
            return pessoa;
        };
    }
}
