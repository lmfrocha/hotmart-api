package br.com.hotmart.api.service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.hotmart.api.model.Category;
import br.com.hotmart.api.model.NewsCategory;
import br.com.hotmart.api.model.Product;
import br.com.hotmart.api.repository.AssessmentRepository;
import br.com.hotmart.api.repository.CategegoryRepository;
import br.com.hotmart.api.repository.NewsCategoryRepository;
import br.com.hotmart.api.repository.ProductRepository;
import br.com.hotmart.api.web.NewsApiResponse;

/**
 * 
 * @author l.rocha
 *
 */
@Service
public class RankingService {

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CategegoryRepository categoryRepository;
	
	@Autowired
	private NewsApiResponse api;
	
	@Autowired
	private NewsCategoryRepository newsCategoryRepository;
	
	@Autowired
	private AssessmentRepository assessmentRepository;
	
	public void updateProductScoreByCategory() {
		Map<Category, Long> quantityPerCategory = new LinkedHashMap<>();
		List<Category> categoryList = categoryRepository.findAll();
		
		if(categoryList.size() > 0) {
			categoryList.forEach(c -> {
				quantityPerCategory.put(c, newsCategoryRepository.findByCategory(c).getTotalResults());
				List<Product> productList = productRepository.findAllByCategory(c);
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
					nc.setTotalResults(api.getNewsByCategory(c));
					newsCategoryRepository.save(nc);
				} else {
					nc.setTotalResults(api.getNewsByCategory(c));
					newsCategoryRepository.save(nc);
				}
			});
		}
	}
	
	
	
	private Double getScore(Category c, Product p, Map<Category, Long> quantityPerCategory) {
		Double score = 0D;
		Double x = assessmentRepository.getAvgScoreByProductAndDateRegister(p, LocalDateTime.now().minusYears(1));
		Double y = ((double) productRepository.count() / (double)ChronoUnit.DAYS.between(p.getDateRegister().toLocalDate(), LocalDateTime.now()));
		Double z = (double) quantityPerCategory.get(c).longValue();
		return score + (x != null ? x : 0D) + (y != null ? y : 0D) + z;
	}
}
