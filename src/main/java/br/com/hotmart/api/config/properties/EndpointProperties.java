package br.com.hotmart.api.config.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * 
 * @author l.rocha
 *
 */
@Configuration
public class EndpointProperties {
	
	@Value("${news-api.url}")
	private String url;
	
	@Value("${news-api.country}")
	private String country;
	
	@Value("${news-api.apikey}")
	private String apiKey;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}
	
}
