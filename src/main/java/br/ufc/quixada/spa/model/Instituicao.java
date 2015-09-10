package br.ufc.quixada.spa.model;

public class Instituicao {

	public Instituicao() {}

	public Instituicao(Integer id) {
		this.id = id;
	}
	
	public Instituicao(Integer id, String sigla, String nome) {
		this.id = id;
		this.sigla = sigla;
		this.nome = nome;
	}

	private Integer id;

	private String sigla;
	
	private String nome;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		return "Instituicao [id=" + id + ", sigla=" + sigla + ", nome=" + nome
				+ "]";
	}

}
