package com.example.Tehtavalista;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.Tehtavalista.controller.TehtavalistaController;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TehtavalistaApplicationTests {

	@Autowired
	private TehtavalistaController tehtavalistaController;

	@Test
	public void contextLoads() throws Exception {
		assertThat(tehtavalistaController).isNotNull();
	}
}
