CREATE TABLE REVINFO (
    REV BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    REVTSTMP bigint
);

CREATE TABLE people_aud (
	id integer not null,
	name varchar(255) not null,
	REV BIGINT(20),
	REVTYPE SMALLINT,
	PRIMARY KEY (id, REV)
);

CREATE TABLE buyer_aud (
	id integer not null,
	people_type integer not null,
	REV BIGINT(20),
	REVTYPE SMALLINT,
	PRIMARY KEY (id, REV)
);

CREATE TABLE seller_aud (
	id integer not null,
	people_type integer not null,
	REV BIGINT(20),
	REVTYPE SMALLINT,
	PRIMARY KEY (id, REV)
);

CREATE TABLE category_aud (
	id integer not null,
	name varchar(255) not null,
	REV BIGINT(20),
	REVTYPE SMALLINT,
	PRIMARY KEY (id, REV)
);

CREATE TABLE product_aud (
	id integer not null,
	name varchar(255) not null,
	description varchar(255) not null,
	category_id integer not null,
	date_register timestamp not null,
	score double default(0),
	REV BIGINT(20),
	REVTYPE SMALLINT,
	PRIMARY KEY (id, REV),
	FOREIGN KEY(category_id) REFERENCES category(id)
);

CREATE TABLE assessment_aud (
	id integer not null,
	sale_id integer not null,
	score integer not null,
	date_register timestamp not null,
	REV BIGINT(20),
	REVTYPE SMALLINT,
	PRIMARY KEY (id, REV)
);

CREATE TABLE sale_aud (
	id integer not null,
	buyer_id integer not null,
	product_id integer not null,
	seller_id integer not null,
	REV BIGINT(20),
	REVTYPE SMALLINT,
	PRIMARY KEY (id, REV),
	FOREIGN KEY(seller_id) REFERENCES seller(id),
	FOREIGN KEY(buyer_id) REFERENCES buyer(id),
	FOREIGN KEY(product_id) REFERENCES product(id)
);

CREATE TABLE news_category_aud (
	id integer not null,
	category_id integer not null,
	total_results integer not null,
	consultation_date timestamp not null,
	REV BIGINT(20),
	REVTYPE SMALLINT,
	PRIMARY KEY (id, REV),
	FOREIGN KEY(category_id) REFERENCES category(id)
);