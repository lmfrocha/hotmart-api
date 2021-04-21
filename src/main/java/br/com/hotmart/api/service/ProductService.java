package br.com.hotmart.api.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.hotmart.api.model.Product;
import br.com.hotmart.api.model.dto.ProductDTO;
import br.com.hotmart.api.model.dto.ProductListDTO;
import br.com.hotmart.api.repository.ProductRepository;
import br.com.hotmart.api.utils.Utils;

/**
 * Class of service corresponding to Products
 * @author l.rocha
 *
 */
@Service
public class ProductService {
	
	@Autowired
	private ProductRepository repository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	/**
	 * Updates the product by id receiving new data
	 * @param id
	 * @param product
	 * @return ProductDTO
	 */
	public ProductDTO update(Long id, Product product) {
		Product old = repository.findById(id).get();
		BeanUtils.copyProperties(product, old, "id");
		repository.save(old);
		return modelMapper.map(old, ProductDTO.class);
	}
	
	/**
	 * Search all products using pagination
	 * @param pageable
	 * @return ProductListDTO
	 */
	public ProductListDTO findAllBy(Pageable pageable) {
		ProductListDTO result = new ProductListDTO(null, Utils.getDateFormatted(), repository.findAllBy(pageable));
		return result;
	}
	
	/**
	 * Search all products using pagination and search term
	 * @param searchTerm
	 * @param pageable
	 * @return ProductListDTO
	 */
	public ProductListDTO searchAllBy(String searchTerm, Pageable pageable) {
		ProductListDTO result = new ProductListDTO(searchTerm, Utils.getDateFormatted(), repository.search(searchTerm, pageable));
		return result;
	}
	
	/**
	 * Get a product by id
	 * @param id
	 * @return ProductDTO
	 */
	public ProductDTO findBy(Long id) {
		Optional<Product> optional = repository.findById(id);
		return optional.isPresent() ? modelMapper.map(optional.get(), ProductDTO.class) : null;
	}

}
