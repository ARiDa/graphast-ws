package org.graphast.ws.config;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Config {

	private static Properties config;

	public static final String USER_HOME = System.getProperty("user.home");

	public static final String CONFIG_FILE = "config.properties";
	
	private static Logger log = LoggerFactory.getLogger(Config.class);
	
	static {
		reload();
	}

	private Config() {	
		super();
	} 

	
	/**
	 * Obtém uma propriedade a partir do arquivo de propriedades que contém
	 * as configurações usadas na aplicação.
	 * Exemplo: Configuration.getProperty("app.environment")
	 * @param key Property name.
	 * @return Property value.
	 */
	public static String getProperty(String key) {
		return config.getProperty(key);
	}	

	/** Carrega ou recarrega as configurações da aplicação a partir do 
	 * arquivo de propriedades config.properties.
	 */
	@SuppressWarnings("unchecked")
	public static void reload() {
		try {
			log.info("user.dir: {}", System.getProperty("user.dir"));
			log.info("user.home: {}", USER_HOME);
			config = new Properties();
			config.load(Config.class.getResourceAsStream("/" + CONFIG_FILE));
			Enumeration<String> props = (Enumeration<String>) config.propertyNames();
            while(props.hasMoreElements()){
                    String s = props.nextElement();
                    config.setProperty(s, config.getProperty(s).replace("${user.home}", USER_HOME));
            }
		} catch (FileNotFoundException e) {
			log.error(e.getMessage(), e);
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		}
	}

	public static Properties getConfig() {
		return config;
	}
}