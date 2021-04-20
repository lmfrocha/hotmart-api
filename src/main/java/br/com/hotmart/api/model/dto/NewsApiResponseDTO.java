package br.com.hotmart.api.model.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NewsApiResponseDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	private Long totalResults;
	
}
