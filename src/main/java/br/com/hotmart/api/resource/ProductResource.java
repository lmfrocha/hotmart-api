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

@RestController
@RequestMapping("/api/v1/product")
public class ProductResource {

	@Autowired
	private ProductRepository repository;
	
	@Autowired
	private ProductService service;
	
	@PostMapping
	private ResponseEntity<?> create(@RequestBody Product product) {
		Product newProduct = repository.save(product);
		return ResponseEntity.status(HttpStatus.CREATED).body(newProduct);
	}

	@GetMapping("/id/{id}")
	private ResponseEntity<ProductDTO> findById(@PathVariable("id") Long id){
		ProductDTO productDTO = service.findBy(id);
		return Objects.nonNull(productDTO) ? ResponseEntity.status(HttpStatus.OK).body(productDTO):ResponseEntity.notFound().build();
	}
	
	@PutMapping("/id/{id}")
	private ResponseEntity<?> update(@PathVariable Long id, @RequestBody Product product) {
		try {
			Product oldProduct = service.update(id, product);
			return ResponseEntity.ok(oldProduct);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("/id/{id}")
	private ResponseEntity<?> delete(@PathVariable Long id) {
		repository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/")
	private ResponseEntity<?> getAll(Pageable pageable){
		ProductListDTO response = service.findAllBy(pageable);
		return response.getProducts().getSize() >0 ? ResponseEntity.ok(response):ResponseEntity.noContent().build();
	}

	@GetMapping("/search")
	private ResponseEntity<?> searchAll(@RequestParam("searchTerm") String searchTerm, Pageable pageable){
		ProductListDTO response = service.searchAllBy(searchTerm, pageable);
		return response.getProducts().getSize() >0 ? ResponseEntity.ok(response):ResponseEntity.noContent().build();
	}
	
}
