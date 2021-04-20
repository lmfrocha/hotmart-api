package br.com.hotmart.api.config.properties;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.springframework.context.annotation.Configuration;

@Configuration
public class EndpointProperties {
	private Properties properties;
	
	public EndpointProperties() {
		try {
			this.properties = getProp();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static Properties getProp() throws IOException {
		Properties props = new Properties();
		FileInputStream file = new FileInputStream(
				".\\src\\main\\resources\\endpoint.properties");
		props.load(file);
		return props;
	}
	
	public Properties getProperties() {
		return this.properties;
	}
	
}
