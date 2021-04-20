package br.com.hotmart.api.model.dto;

import java.io.Serializable;

import org.springframework.data.domain.Page;

import br.com.hotmart.api.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductListDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	private String searchTerm;
	private String currentDate;
	private Page<Product> products;
}
