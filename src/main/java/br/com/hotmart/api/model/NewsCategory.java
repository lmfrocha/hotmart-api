package br.com.hotmart.api.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

import com.sun.istack.NotNull;

import lombok.Data;

/**
 * 
 * @author l.rocha
 *
 */
@Audited
@Data
@Entity
@Table(name = "news_category")
public class NewsCategory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;

	@Column(name = "total_results", nullable = true)
	private Long totalResults;
	
	@Column(name = "consultation_date")
	private LocalDateTime consultationDate;
}
