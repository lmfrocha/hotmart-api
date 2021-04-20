package br.com.hotmart.api.service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.hotmart.api.config.properties.EndpointProperties;
import br.com.hotmart.api.model.Category;
import br.com.hotmart.api.model.NewsCategory;
import br.com.hotmart.api.model.Product;
import br.com.hotmart.api.model.dto.NewsApiResponseDTO;
import br.com.hotmart.api.repository.AssessmentRepository;
import br.com.hotmart.api.repository.CategegoryRepository;
import br.com.hotmart.api.repository.NewsCategoryRepository;
import br.com.hotmart.api.repository.ProductRepository;

@Service
public class RankingService {

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CategegoryRepository categoryRepository;
	
	@Autowired
	private EndpointProperties endpointProperties;
	
	@Autowired
	private NewsCategoryRepository newsCategoryRepository;
	
	@Autowired
	private AssessmentRepository assessmentRepository;
	
	public void updateProductScoreByCategory() {
		Map<Category, Long> quantityPerCategory = new LinkedHashMap<>();
		List<Category> categoryList = categoryRepository.findAll();
		
		if(categoryList.size() > 0) {
			categoryList.forEach(c -> {
				//calcular quantidade por categoria
				quantityPerCategory.put(c, newsCategoryRepository.findByCategory(c).getTotalResults());
				//listar produtos por categoria
				List<Product> productList = productRepository.findAllByCategory(c);
				//atribui o score para cada produto
				productList.forEach(p -> {
					Double score = getScore(c, p, quantityPerCategory);
					p.setScore(score);
				});
				productRepository.saveAll(productList);
			});
		}
	}
	
	public void updateNewsCategoryResults() {
		List<Category> categoryList = categoryRepository.findAll();
		if(categoryList.size() > 0) {
			categoryList.forEach(c-> {
				NewsCategory nc = newsCategoryRepository.findByCategory(c);
				if(Objects.isNull(nc)) {
					nc = new NewsCategory();
					nc.setCategory(c);
					nc.setConsultationDate(LocalDateTime.now());
					nc.setTotalResults(cathNewsCategory(c));
					newsCategoryRepository.save(nc);
				} else {
					nc.setTotalResults(cathNewsCategory(c));
					newsCategoryRepository.save(nc);
				}
			});
		}
	}
	
	
	private Long cathNewsCategory(Category category) {
		String url = endpointProperties.getProperties().getProperty("news-api.url") 
				+ "country=" + endpointProperties.getProperties().getProperty("news-api.country") + "&" 
				+ "category=" + category.getName() + "&" 
				+ "apiKey="	+ endpointProperties.getProperties().getProperty("news-api.apikey");
		RestTemplate restTemplate = new RestTemplate();
		NewsApiResponseDTO response = restTemplate.getForObject(url, NewsApiResponseDTO.class, "totalResults");
		return response.getTotalResults();
	}
	
	private Double getScore(Category c, Product p, Map<Category, Long> quantityPerCategory) {
		Double score = 0D;
		Double x = assessmentRepository.getAvgScoreByProductAndDateRegister(p, LocalDateTime.now().minusYears(1));
		Double y = ((double) productRepository.count() / (double)ChronoUnit.DAYS.between(p.getDateRegister().toLocalDate(), LocalDateTime.now()));
		Double z = (double) quantityPerCategory.get(c).longValue();
		return score + (x != null ? x : 0D) + (y != null ? y : 0D) + z;
	}
}
