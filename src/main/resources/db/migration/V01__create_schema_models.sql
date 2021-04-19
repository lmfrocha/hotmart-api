CREATE TABLE people (
	id integer PRIMARY KEY AUTO_INCREMENT,
	name varchar(255) not null
);

CREATE TABLE buyer (
	id integer PRIMARY KEY AUTO_INCREMENT,
	people_type integer not null
);

CREATE TABLE seller (
	id integer PRIMARY KEY AUTO_INCREMENT,
	people_type integer not null
);

CREATE TABLE category (
	id integer PRIMARY KEY AUTO_INCREMENT,
	name varchar(255) not null
);

CREATE TABLE product (
	id integer PRIMARY KEY AUTO_INCREMENT,
	name varchar(255) not null,
	description varchar(255) not null,
	category_id integer,
	date_register timestamp not null,
	score double default(0),
	FOREIGN KEY(category_id) REFERENCES category(id)
);

CREATE TABLE assessment (
	id integer PRIMARY KEY AUTO_INCREMENT,
	sale_id integer not null,
	score integer not null
);

CREATE TABLE sale (
	id integer PRIMARY KEY AUTO_INCREMENT,
	assessment integer,
	buyer_id integer not null,
	product_id integer not null,
	seller_id integer not null,
	FOREIGN KEY(seller_id) REFERENCES seller(id),
	FOREIGN KEY(buyer_id) REFERENCES buyer(id),
	FOREIGN KEY(product_id) REFERENCES product(id)
);
