package com.springbatch.arquivomultiplosformatos.dominio;


import java.util.ArrayList;
import java.util.List;

public class Cliente {

	private String nome;
	private String sobrenome;
	private String idade;
	private String email;
	private List<Transacao> transacaos = new ArrayList<>();

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getIdade() {
		return idade;
	}

	public void setIdade(String idade) {
		this.idade = idade;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setTransacaos(List<Transacao> transacaos) {
		this.transacaos = transacaos;
	}

	public List<Transacao> getTransacaos() {
		return transacaos;
	}

	@Override
	public String toString() {
		return "Cliente{" + "nome='" + nome + "'" + ", sobrenome ='" + sobrenome + "'" + ", idade='" + idade + "'"
				+ ", email='" + email + "'" +
		(transacaos.isEmpty() ? "" : ", transacoes= " + transacaos) +'}';
	}
}
