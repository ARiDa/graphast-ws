package org.graphast.ws.model;


public class Atividade {

	public Atividade() {}
	
	public Atividade(Integer id) {
		this.id = id;
	}
	
	public Atividade(Integer id, String nome, Integer qtdVagas) {
		super();
		this.id = id;
		this.nome = nome;
		this.qtdVagas = qtdVagas;
	}

	private Integer id;
	
	private String nome;
	
	private Integer qtdVagas;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getQtdVagas() {
		return qtdVagas;
	}

	public void setQtdVagas(Integer qtdVagas) {
		this.qtdVagas = qtdVagas;
	}

	@Override
	public String toString() {
		return "Atividade [id=" + id + ", nome=" + nome + ", qtdVagas="
				+ qtdVagas + "]";
	}
	
}
