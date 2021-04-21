package br.com.hotmart.api.resource;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.hotmart.api.model.Product;
import br.com.hotmart.api.model.dto.ProductDTO;
import br.com.hotmart.api.model.dto.ProductListDTO;
import br.com.hotmart.api.repository.ProductRepository;
import br.com.hotmart.api.service.ProductService;

/**
 * Controller that makes resources available to handle the resources of the Product entity
 * @author l.rocha
 *
 */
@RestController
@RequestMapping("/api/v1/product")
public class ProductResource {

	@Autowired
	private ProductRepository repository;
	
	@Autowired
	private ProductService service;
	
	/**
	 * Create a product
	 * @param product
	 * @return ResponseEntity<Product>
	 */
	@PostMapping
	private ResponseEntity<?> create(@RequestBody Product product) {
		Product newProduct = repository.save(product);
		return ResponseEntity.status(HttpStatus.CREATED).body(newProduct);
	}

	/**
	 * Return product by Id
	 * @param id
	 * @return ResponseEntity<ProductDTO>
	 */
	@GetMapping("/id/{id}")
	private ResponseEntity<ProductDTO> findById(@PathVariable("id") Long id){
		ProductDTO productDTO = service.findBy(id);
		return Objects.nonNull(productDTO) ? ResponseEntity.status(HttpStatus.OK).body(productDTO) : ResponseEntity.notFound().build();
	}
	
	/**
	 * Updates a product resource
	 * @param id
	 * @param product
	 * @return ResponseEntity<ProductDTO>
	 */
	@PutMapping("/id/{id}")
	private ResponseEntity<ProductDTO> update(@PathVariable Long id, @RequestBody Product product) {
		try {
			ProductDTO oldProduct = service.update(id, product);
			return ResponseEntity.ok(oldProduct);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	/**
	 * Delete a product resource by id
	 * @param id
	 * @return ResponseEntity<HttpStatus.noContent>
	 */
	@DeleteMapping("/id/{id}")
	private ResponseEntity<?> delete(@PathVariable Long id) {
		repository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	/**
	 * Search all products using pagination
	 * @param pageable
	 * @return ResponseEntity<ProductListDTO>
	 */
	@GetMapping("/")
	private ResponseEntity<ProductListDTO> getAll(Pageable pageable){
		ProductListDTO response = service.findAllBy(pageable);
		return response.getProducts().getSize() >0 ? ResponseEntity.ok(response):ResponseEntity.noContent().build();
	}

	/**
	 * Search all products using pagination and search term
	 * @param searchTerm
	 * @param pageable
	 * @return ResponseEntity<ProductListDTO>
	 */
	@GetMapping("/search")
	private ResponseEntity<ProductListDTO> searchAll(@RequestParam("searchTerm") String searchTerm, Pageable pageable){
		ProductListDTO response = service.searchAllBy(searchTerm, pageable);
		return response.getProducts().getSize() >0 ? ResponseEntity.ok(response):ResponseEntity.noContent().build();
	}
	
}
