package com.example.Tehtavalista;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.Tehtavalista.domain.Person;
import com.example.Tehtavalista.domain.Task;
import com.example.Tehtavalista.domain.TaskRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TaskRepositoryTest {

	@Autowired
	private TaskRepository repository;

	@Test
	public void createNewTask() {
		Task task = new Task("Ellenin synttärit", null, null, new Person("Mummi"));
		repository.save(task);
		assertThat(task.getTaskid()).isNotNull();
	}

	@Test
	public void deleteTest() {

		Task task = new Task("Ellenin synttärit", null, null, new Person("Mummi"));
		repository.save(task);

		repository.delete(task);
		assertThat(task.getTaskid()).isNotNull();

	}

}
