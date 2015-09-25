package org.graphast.ws.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import org.graphast.model.Graph;
import org.graphast.query.route.shortestpath.AbstractShortestPathService;
import org.graphast.query.route.shortestpath.dijkstra.DijkstraConstantWeight;
import org.graphast.query.route.shortestpath.model.Path;
import org.graphast.ws.enumeration.ResponseStatus;
import org.graphast.ws.model.Atividade;
import org.graphast.ws.model.LoadedGraph;
import org.graphast.ws.model.ResponseStatusMessage;
import org.graphast.ws.model.ShortestPathResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Named
@RequestMapping("/shortestpath")
public class ShortestPathController {

	private Logger log = LoggerFactory.getLogger(this.getClass());
		
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody List<Atividade> findAll() {
		log.debug("Atividade - GET (all)");
		Atividade atividade = new Atividade(1, "Curso SQL", 20);
		List<Atividade> atividades = new ArrayList<Atividade>();
		atividades.add(atividade);
		return atividades; 
		//return atividadeService.find(Atividade.class);
	}
	
	@RequestMapping(value="{lat1}/{long1}/{lat2}/{long2}", method = RequestMethod.GET)
	public @ResponseBody ShortestPathResult shortestPath(@PathVariable Double lat1, 
			@PathVariable Double long1, @PathVariable Double lat2, @PathVariable Double long2) {
		log.debug("Atividade - GET (id)");
		Graph graph;
		try {
			graph = LoadedGraph.getInstance().getGraph();

			
			AbstractShortestPathService sp = new DijkstraConstantWeight(graph);
			long source = graph.getNodeId(lat1, long1);
			long target = graph.getNodeId(lat2, long2);
			Path path = sp.shortestPath(source, target);
			
			ShortestPathResult spr = new ShortestPathResult();
			spr.generateResult(graph, path);
			
			
			return spr;

		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody ResponseStatusMessage insert(Atividade atividade) {
		log.debug("Atividade - POST");
		return new ResponseStatusMessage(ResponseStatus.SUCCESS, "Atividade inserida com sucesso");
	}

	@RequestMapping(value="{idAtividade}/participantes/{idParticipante}", method = RequestMethod.POST)
	public @ResponseBody ResponseStatusMessage insereParticipanteEmAtividade(@PathVariable Integer idAtividade, @PathVariable Integer idParticipante) {
		log.debug("Atividade - POST - insere participante em atividade");
		String msg = null;
		return new ResponseStatusMessage(ResponseStatus.SUCCESS, msg);
	}
	
	@RequestMapping(value="{id}", method = RequestMethod.DELETE)
	public @ResponseBody ResponseStatusMessage delete(@PathVariable Integer id) {
		log.debug("Atividade - DELETE");
		return new ResponseStatusMessage(ResponseStatus.SUCCESS, "Atividade removida com sucesso");
	}
	
	@RequestMapping(value="{idAtividade}/participantes/{idParticipante}", method = RequestMethod.DELETE)
	public @ResponseBody ResponseStatusMessage removeParticipanteDeAtividade(@PathVariable Integer idAtividade, @PathVariable Integer idParticipante) {
		log.debug("Atividade - DELETE");
		return new ResponseStatusMessage(ResponseStatus.SUCCESS, "ok");
	}

	@RequestMapping(value="{id}", method = RequestMethod.PUT)
	public @ResponseBody ResponseStatusMessage update(Atividade atividade, @PathVariable Integer id) {
		log.debug("Atividade - PUT");
		atividade.setId(id);
		log.debug("Updating Atividade: {}", atividade);
		return new ResponseStatusMessage(ResponseStatus.SUCCESS, "Atividade atualizada com sucesso");
	}
	
}
