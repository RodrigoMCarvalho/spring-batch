package com.springbatch.skipexception.reader;

import javax.sql.DataSource;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.springbatch.skipexception.dominio.Cliente;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

@Configuration
public class SkipExceptionReaderConfig {

	@Bean
	public ItemReader<Cliente> skipExceptionReader(@Qualifier("appDataSource") DataSource dataSource) {
		return new JdbcCursorItemReaderBuilder<Cliente>()
				.name("skipExceptionReader")
				.dataSource(dataSource)
				.sql("select * from cliente")
				.rowMapper(rowMapperFalha())
				.build();
	}

	//Força erro no último cliente cadastrado no banco
	//Mesmo os demais sendo processados com sucesso, não é impresso nenhum cliente
	//Adicionado o faultTolerant() no steap para evitar isso
	private RowMapper<Cliente> rowMapperFalha() {

		return new RowMapper() {

			@Override
			public Object mapRow(ResultSet rs, int i) throws SQLException {
				if (rs.getRow()  == 11)
					throw new SQLException((String.format("Encerrando a execução - Cliente inválido %s", rs.getString("email"))));
				 else
					return ClienteRowMapper(rs);
			}

				private Cliente ClienteRowMapper(ResultSet rs) throws SQLException {
					Cliente cliente = new Cliente();
					cliente.setNome(rs.getString("nome"));
					cliente.setSobrenome(rs.getString("sobrenome"));
					cliente.setEmail(rs.getString("email"));
					cliente.setIdade(rs.getString("idade"));
					return cliente;
				}
		};
	}


}
