package br.com.hotmart.api.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.hotmart.api.model.Product;
import br.com.hotmart.api.repository.ProductRepository;

@RestController
@RequestMapping("/api/v1/product")
public class ProductResource {

	@Autowired
	private ProductRepository repository;

	@PostMapping
	private ResponseEntity<?> create(@RequestBody Product product) {
		Product newProduct = repository.save(product);
		return ResponseEntity.status(HttpStatus.CREATED).body(newProduct);
	}

}
