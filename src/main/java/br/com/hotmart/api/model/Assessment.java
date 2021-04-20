package br.com.hotmart.api.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import com.sun.istack.NotNull;

import lombok.Data;

@Data
@Entity
@Table(name = "assessment")
public class Assessment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@OneToOne
	@JoinColumn(name = "sale_id")
	private Sale sale;
	
	@Column(name = "score", nullable = true)
	private Integer score;
	
	@Column(name = "date_register", nullable = false)
	private LocalDateTime dateRegister;
	
	@PrePersist
	public void prePersist() {
		setDateRegister(LocalDateTime.now());
	}
}
