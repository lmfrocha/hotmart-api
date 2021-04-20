package br.com.hotmart.api.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.hotmart.api.model.Product;
import br.com.hotmart.api.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository repository;
	
	public Product update(Long id, Product product) {
		Product old = repository.findById(id).get();
		BeanUtils.copyProperties(product, old, "id");
		return repository.save(old);
	}
	
}
