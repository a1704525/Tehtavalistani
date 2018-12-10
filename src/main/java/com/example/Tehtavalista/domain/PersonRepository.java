package com.example.Tehtavalista.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface PersonRepository extends CrudRepository<Person, Long> {

	List<Person> findByName(@Param("name") String name);

}
