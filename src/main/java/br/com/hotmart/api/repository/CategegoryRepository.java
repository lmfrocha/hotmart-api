package br.com.hotmart.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.hotmart.api.model.Category;

/**
 * 
 * @author l.rocha
 *
 */
@Repository
public interface CategegoryRepository extends JpaRepository<Category, Long> {

}
