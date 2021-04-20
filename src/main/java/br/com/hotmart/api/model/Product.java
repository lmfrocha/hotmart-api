package br.com.hotmart.api.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sun.istack.NotNull;

import lombok.Data;

@Data
@Entity
@Table(name = "product")
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Column(name = "name", nullable = false)
	private String name;

	@NotNull
	@Column(name = "description")
	private String description;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Column(name = "date_register", updatable = false)
	private LocalDateTime dateRegister;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;

	@Column(name = "score", nullable = true)
	private Double score;

	@PrePersist
	public void prePersist() {
		setDateRegister(LocalDateTime.now());
	}

}