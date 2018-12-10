package com.example.Tehtavalista;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.Tehtavalista.domain.Person;
import com.example.Tehtavalista.domain.PersonRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PersonRepositoryTest {

	@Autowired
	private PersonRepository personRepository;

	@Test
	public void createTest() {
		Person person = new Person("Mummi");
		personRepository.save(person);

		personRepository.save(person);
		assertThat(person.getPersonid()).isNotNull();
	}

	@Test
	public void deleteTest() {

		Person person = new Person("Mummi");
		personRepository.save(person);

		personRepository.delete(person);
		assertThat(person.getPersonid()).isNotNull();

	}

}
