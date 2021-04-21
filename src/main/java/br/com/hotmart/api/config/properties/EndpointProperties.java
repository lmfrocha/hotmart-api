package br.com.hotmart.api.config.properties;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.springframework.context.annotation.Configuration;

/**
 * Class to load the endpoint properties saved in the endpoint.properties file
 * @author l.rocha
 *
 */
@Configuration
public class EndpointProperties {
	private Properties properties;
	
	/**
	 * Constructor
	 */
	public EndpointProperties() {
		try {
			this.properties = getProp();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Load all Properties loaded by file
	 * @return Properties
	 * @throws IOException
	 */
	private static Properties getProp() throws IOException {
		Properties props = new Properties();
		FileInputStream file = new FileInputStream(
				".\\src\\main\\resources\\endpoint.properties");
		props.load(file);
		return props;
	}
	
	/**
	 * Public method to return properties
	 * @return properties
	 */
	public Properties getProperties() {
		return this.properties;
	}
	
}
