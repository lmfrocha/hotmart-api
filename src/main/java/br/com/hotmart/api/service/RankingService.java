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
 * Class responsible for performing ranking operations
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
	
	/**
	 * Method that loads all registered categories where 
	 * the amount of news and products related to it is loaded 
	 * one at a time, where the list of products is scrolled one by one, 
	 * updating its Score attribute
	 */
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
	
	/**
	 * Method that lists all the categories registered in the system and 
	 * through an external api updates the amount of harmfulness related 
	 * to the category.
	 */
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
	
	/**
	 * Score calculation method:
	 * • X = Average product evaluation over the last 12 months
	 * • Y = Quantity of sales / days that the product exists
	 * • Z = Quantity of news from the product category on the current day
	 *	Score = (X + Y + Z)
	 * @param category
	 * @param product
	 * @param quantityPerCategory
	 * @return Score
	 */
	private Double getScore(Category category, Product product, Map<Category, Long> quantityPerCategory) {
		Double score = 0D;
		Double x = assessmentRepository.getAvgScoreByProductAndDateRegister(product, LocalDateTime.now().minusYears(1));
		Double y = ((double) productRepository.count() / (double)ChronoUnit.DAYS.between(product.getDateRegister().toLocalDate(), LocalDateTime.now()));
		Double z = (double) quantityPerCategory.get(category).longValue();
		return score + (x != null ? x : 0D) + (y != null ? y : 0D) + z;
	}
}
