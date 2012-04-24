package br.com.kohen.eventmanager.clarion.email;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import br.com.kohen.eventmanager.commons.config.KProperties;

public class PropertyRead {

	private static final Log LOG = LogFactory.getLog(PropertyRead.class);
	
	private static KProperties properties;
	
	
	
	
	public static String getProperty(String key) {
		try {
			loadProperties();
		} catch (IOException e) {
			LOG.error("###### Erro ao acessar o property no modulo Clarion", e);
		}
	
		return properties.get(key, String.class);
	}
	
	
	private static void loadProperties() throws IOException {
		
		if (properties == null) {
			KProperties properties = new KProperties();
			InputStream resourceAsStream = NotificationEmailSend.class.getResourceAsStream("/clarion-config.properties");
			
			properties.load(resourceAsStream);
		}
		
	}
	
}
