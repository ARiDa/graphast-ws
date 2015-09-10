package br.ufc.quixada.spa.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Atividade {

	public Atividade() {}
	
	public Atividade(Integer id) {
		this.id = id;
	}
	
	public Atividade(Integer id, String nome, Integer qtdVagas,
			List<Participante> participantes) {
		super();
		this.id = id;
		this.nome = nome;
		this.qtdVagas = qtdVagas;
		this.participantes = participantes;
	}

	private Integer id;
	
	private String nome;
	
	private Integer qtdVagas;

	@JsonIgnore
	private List<Participante> participantes;
	
	
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

	public List<Participante> getParticipantes() {
		return participantes;
	}

	public void setParticipantes(List<Participante> participantes) {
		this.participantes = participantes;
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
