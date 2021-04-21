package br.com.hotmart.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.hotmart.api.model.Category;
import br.com.hotmart.api.model.NewsCategory;

/**
 * 
 * @author l.rocha
 *
 */
@Repository
public interface NewsCategoryRepository extends JpaRepository<NewsCategory, Long> {

	NewsCategory findByCategory(Category category);
	
}
