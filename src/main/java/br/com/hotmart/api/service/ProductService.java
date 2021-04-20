package br.com.hotmart.api.service;

import java.util.Objects;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.hotmart.api.model.Product;
import br.com.hotmart.api.model.dto.ProductDTO;
import br.com.hotmart.api.model.dto.ProductListDTO;
import br.com.hotmart.api.repository.ProductRepository;
import br.com.hotmart.api.utils.Utils;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository repository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public Product update(Long id, Product product) {
		Product old = repository.findById(id).get();
		BeanUtils.copyProperties(product, old, "id");
		return repository.save(old);
	}
	
	public ProductListDTO findAllBy(Pageable pageable) {
		ProductListDTO result = new ProductListDTO(null, Utils.getDateFormatted(), repository.findAllBy(pageable));
		return result;
	}
	
	public ProductListDTO searchAllBy(String searchTerm, Pageable pageable) {
		ProductListDTO result = new ProductListDTO(searchTerm, Utils.getDateFormatted(), repository.search(searchTerm, pageable));
		return result;
	}
	
	public <D, T> Page<D> mapEntityPageIntoDtoPage(Page<T> entities, Class<D> dtoClass) {
	    return entities.map(objectEntity -> modelMapper.map(objectEntity, dtoClass));
	}

	public ProductDTO findBy(Long id) {
		Product product = repository.findById(id).get();
		return Objects.nonNull(product) ? modelMapper.map(product, ProductDTO.class) : null;
	}

}
