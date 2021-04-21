package br.com.hotmart.api.model;

import java.io.Serializable;

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
@Table(name = "sale")
public class Sale implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "seller_id", nullable = false)
	private Seller seller;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "buyer_id", nullable = false)
	private Buyer buyer;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "product_id", nullable = false)
	private Product product;
	
}
