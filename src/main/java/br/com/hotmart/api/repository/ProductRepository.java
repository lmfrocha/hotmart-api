package br.com.hotmart.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.hotmart.api.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	@Query("from Product p "
			+ "inner join p.category as c "
			+ "order by "
			+ "p.score desc, "
			+ "p.name asc, "
			+ "c.name asc")
	Page<Product> findAllBy(Pageable pageable);

}
