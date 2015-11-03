package org.graphast.ws.controller;

import java.util.List;
import java.util.Map;

import javax.inject.Named;

import org.graphast.app.GraphInfo;
import org.graphast.app.GraphService;
import org.graphast.config.Configuration;
import org.graphast.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Named
@RequestMapping("/admin")
public class AdminController {

	private GraphService service = new GraphService();
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
		
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody String index() {
		log.debug("admin service");
		return "Admin service";
	}

	@RequestMapping(value="/apps", method = RequestMethod.GET)
	public @ResponseBody List<GraphInfo> apps() {
		log.debug("apps");
		return Configuration.getApps();
	}

	@RequestMapping(value="/app-names", method = RequestMethod.GET)
	public @ResponseBody List<String> appNames() {
		log.debug("app-names");
		return Configuration.getAppNames();
	}
	
	@RequestMapping(value="/load/{app}", method = RequestMethod.GET)
	public @ResponseBody GraphInfo load(@PathVariable String app) {
		log.debug("load {}", app);
		return service.load(app);
	}
	
	@RequestMapping(value="/create", method = RequestMethod.POST)
	public @ResponseBody GraphInfo create(@RequestParam Map<String, String> params) {
		log.debug("create");
		GraphInfo graphInfo = new GraphInfo();
		graphInfo.setAppName(params.get("app"));
		graphInfo.setCosts(params.get("costs"));
		graphInfo.setGraphDir(params.get("dir"));
		graphInfo.setImporter(params.get("importer"));
		graphInfo.setNetwork(params.get("network"));
		graphInfo.setPoiCategoryFilter(StringUtils.splitIntToList(",", params.get("poi-category-filter")));
		graphInfo.setPois(params.get("pois"));
		graphInfo.setQueryServices(params.get("query-services"));
		service.create(graphInfo);
		return graphInfo;
	}
	
}
