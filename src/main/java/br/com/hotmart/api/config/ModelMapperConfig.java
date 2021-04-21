package br.com.hotmart.api.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Model Mapper configuration class
 * @author l.rocha
 *
 */
@Configuration
public class ModelMapperConfig {

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
