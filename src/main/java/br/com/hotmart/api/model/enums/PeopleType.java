package br.com.hotmart.api.model.enums;

public enum PeopleType {

	BUYER(1, "Buyer"), 
	SELLER(2, "Seller");

	private Integer id;
	private String description;

	private PeopleType(Integer id, String description) {
		this.id = id;
		this.description = description;
	}

	public Integer getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public static PeopleType toEnum(Integer id) {
		if (id == null) {
			return null;
		}

		for (PeopleType x : PeopleType.values()) {
			if (id.equals(x.id)) {
				return x;
			}
		}

		throw new IllegalArgumentException("Id is not valid: " + id);
	}

}