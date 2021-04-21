package br.com.hotmart.api.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import br.com.hotmart.api.config.properties.EndpointProperties;
import br.com.hotmart.api.model.Category;
import br.com.hotmart.api.model.dto.NewsApiResponseDTO;

/**
 * Class that consumes the news API
 * @author l.rocha
 *
 */
@Service
public class NewsApiResponse {
	
	@Autowired
	private EndpointProperties endpointProperties;
	
	/**
	 * Method that consumes the api
	 * @param category
	 * @return Long amount of news by category
	 */
	public Long getNewsByCategory(Category category) {
		String url = endpointProperties.getUrl() 
				+ "country=" + endpointProperties.getCountry() + "&" 
				+ "category=" + category.getName() + "&" 
				+ "apiKey="	+ endpointProperties.getApiKey();
		
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
