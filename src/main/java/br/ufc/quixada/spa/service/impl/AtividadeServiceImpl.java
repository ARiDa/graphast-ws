package br.ufc.quixada.spa.service.impl;

import java.util.List;
import java.util.Map;

import br.ufc.quixada.npi.enumeration.QueryType;
import br.ufc.quixada.npi.util.SimpleMap;
import br.ufc.quixada.spa.model.Atividade;
import br.ufc.quixada.spa.model.Participante;
import br.ufc.quixada.spa.service.AtividadeService;
import br.ufc.quixada.spa.service.ParticipanteService;

public class AtividadeServiceImpl implements AtividadeService {

	private ParticipanteService participanteService;
	
	public String insereParticipanteEmAtividade(Integer idParticipante, Integer idAtividade) {
		return participanteService.insereAtividadeEmParticipante(idAtividade, idParticipante);
	}
	
	public String removeParticipanteDeAtividade(Integer idParticipante, Integer idAtividade) {
		int n = executeUpdate("delete from participante_atividade where participante_id = :idParticipante and atividade_id = :idAtividade", 
				new SimpleMap<String, Object>("idParticipante", idParticipante, "idAtividade", idAtividade));
		if (n == 0) {
			return "Participante não estava cadastrado na atividade";
		} else {
			return "Participante removido com sucesso da atividade";
		}
	}
	
	public List<Participante> findParticipantesById(Integer id) {
		Atividade atividade = find(Atividade.class, id);
		return atividade.getParticipantes();
	}

	@Override
	public void save(Atividade entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Atividade entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Atividade entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Atividade find(Class<Atividade> entityClass, Object id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Atividade> find(Class<Atividade> entityClass) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Atividade> find(Class<Atividade> entityClass, int firstResult,
			int maxResults) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List find(String queryName, Map<String, Object> namedParams) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List find(QueryType type, String query,
			Map<String, Object> namedParams) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List find(String queryName, Map<String, Object> namedParams,
			int firstResult, int maxResults) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List find(QueryType type, String query,
			Map<String, Object> namedParams, int firstResult, int maxResults) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object findFirst(String query, Map<String, Object> namedParams) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object findFirst(QueryType type, String query,
			Map<String, Object> namedParams) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int executeUpdate(String sql, Map<String, Object> namedParams) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
