package br.com.hotmart.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.PrePersist;
import javax.persistence.PrimaryKeyJoinColumn;

import org.hibernate.envers.Audited;

import br.com.hotmart.api.model.enums.PeopleType;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * @author l.rocha
 *
 */
@Audited
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Buyer extends People{
	private static final long serialVersionUID = 1L;
	
	@Enumerated
	@Column(name = "people_type", nullable = false)
	private PeopleType peopleType;
	
	@PrePersist
	public void prePersist() {
		setPeopleType(PeopleType.BUYER);
	}
}
