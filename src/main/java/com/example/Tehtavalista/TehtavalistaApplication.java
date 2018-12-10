package com.example.Tehtavalista;

import org.slf4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.format.FormatterRegistry;

import com.example.Tehtavalista.domain.Person;
import com.example.Tehtavalista.domain.PersonRepository;
import com.example.Tehtavalista.domain.Task;
import com.example.Tehtavalista.domain.TaskRepository;
import com.example.Tehtavalista.domain.User;
import com.example.Tehtavalista.domain.UserRepository;
import com.fasterxml.jackson.annotation.JsonFormat;

@SpringBootApplication
public class TehtavalistaApplication {

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")

	private static final Logger log = org.slf4j.LoggerFactory.getLogger(TehtavalistaApplication.class);

	public static void main(String[] args) /* throws Exception */ {
		SpringApplication.run(TehtavalistaApplication.class, args);
	}

	@Bean
	public CommandLineRunner taskDemo(TaskRepository trepository, PersonRepository prepository,
			UserRepository urepository) {

		return (args) -> {
			log.info("saving tasks");

			prepository.save(new Person(""));
			prepository.save(new Person("Äiti"));
			prepository.save(new Person("Lydia"));
			prepository.save(new Person("Sofia"));
			prepository.save(new Person("Isi"));

			trepository.save(new Task("Ohjelmoinnin tehtävät", null, null, prepository.findByName("Äiti").get(0)));
			trepository.save(new Task("Koris", null, null, prepository.findByName("Lydia").get(0)));
			trepository.save(new Task("Luistelu", null, null, prepository.findByName("Sofia").get(0)));
			trepository.save(new Task("Siivous", null, null, prepository.findByName("Isi").get(0)));

			// Create users: admin/admin user/user
			User user1 = new User("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER");
			User user2 = new User("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "ADMIN");
			urepository.save(user1);
			urepository.save(user2);

			log.info("fetch all tasks");
			for (Task task : trepository.findAll()) {
				log.info(task.toString());
			}

		};
	}

}