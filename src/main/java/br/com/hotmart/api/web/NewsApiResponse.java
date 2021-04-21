package br.com.hotmart.api.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import br.com.hotmart.api.config.properties.EndpointProperties;
import br.com.hotmart.api.model.Category;
import br.com.hotmart.api.model.dto.NewsApiResponseDTO;

/**
 * 
 * @author l.rocha
 *
 */
@Service
public class NewsApiResponse {
	
	@Autowired
	private EndpointProperties endpointProperties;
	
	public Long getNewsByCategory(Category category) {
		String url = endpointProperties.getProperties().getProperty("news-api.url") 
				+ "country=" + endpointProperties.getProperties().getProperty("news-api.country") + "&" 
				+ "category=" + category.getName() + "&" 
				+ "apiKey="	+ endpointProperties.getProperties().getProperty("news-api.apikey");
		
		RestTemplate restTemplate = new RestTemplate();
		NewsApiResponseDTO response = null;
		try {
			response = restTemplate.getForObject(url, NewsApiResponseDTO.class, "totalResults");
		} catch (RestClientException e) {
			e.printStackTrace();
		}
		return response.getTotalResults() != null ? response.getTotalResults() : 0L;
	}
	
}
