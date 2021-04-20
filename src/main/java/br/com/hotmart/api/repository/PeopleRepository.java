package br.com.hotmart.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.hotmart.api.model.People;

public interface PeopleRepository extends JpaRepository<People, Long> {

}
