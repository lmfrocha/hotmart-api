package br.com.hotmart.api.repository;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.hotmart.api.model.Assessment;
import br.com.hotmart.api.model.Product;

/**
 * 
 * @author l.rocha
 *
 */
@Repository
public interface AssessmentRepository extends JpaRepository<Assessment, Long> {

	@Query(" select AVG(a.score) from Assessment a "
			+ "inner join a.sale s "
			+ "inner join s.product p "
			+ "where "
			+ "a.dateRegister >=:dateTime and "
			+ "p =:product"
			)
	Double getAvgScoreByProductAndDateRegister(Product product, LocalDateTime dateTime);
}
