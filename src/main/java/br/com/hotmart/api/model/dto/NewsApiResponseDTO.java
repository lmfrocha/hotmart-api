package br.com.hotmart.api.model.dto;

import java.io.Serializable;

import lombok.Data;

/**
 * 
 * @author l.rocha
 *
 */
@Data
public class NewsApiResponseDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	private Long totalResults;
	
}
