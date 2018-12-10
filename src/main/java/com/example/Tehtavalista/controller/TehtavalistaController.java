package com.example.Tehtavalista.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.Tehtavalista.domain.Person;
import com.example.Tehtavalista.domain.PersonRepository;
import com.example.Tehtavalista.domain.Task;
import com.example.Tehtavalista.domain.TaskRepository;

@Controller
public class TehtavalistaController {

	@Autowired
	private TaskRepository repository;

	@Autowired
	private PersonRepository prepository;

	@RequestMapping(value = "/index")
	public String index() {
		return "index";
	}

	@RequestMapping(value = "/login")
	public String login() {
		return "login";
	}
	
	// Show all tasks from one person
	@RequestMapping(value = "/person/{pid}/tasklist")
	public String persontask(@PathVariable("pid") Long personId, Model model) {
		Optional<Person> person = prepository.findById(personId);
		Person person1 = person.get();
		model.addAttribute("tasks", person1.getTasks());
		return "persontask";
	}

	// Show all tasks
	@RequestMapping(value = "/tasklist")
	public String taskList(Model model) {
		model.addAttribute("tasks", repository.findAll());
		return "tasklist";
	}

	/*
	 * //Show person id tasks
	 * 
	 * @GetMapping(value = "/person/{id}/tasklist") public @ResponseBody
	 * List<Task>personTaskList(@PathVariable("id") Long personId, Model model)
	 * {
	 * 
	 * Optional<Person> person = prepository.findById(personId); Person person1
	 * = person.get();
	 * 
	 * if (person1 != null) { return person1.getTasks(); } else { return new
	 * ArrayList<Task>(); }
	 * 
	 * }
	 */

	// RESTful service to get all tasks
	@RequestMapping(value = "/tasks", method = RequestMethod.GET)
	public @ResponseBody List<Task> taskListRest() {
		return (List<Task>) repository.findAll();
	}

	// RESTful service to get task by id
	@RequestMapping(value = "/task/{id}", method = RequestMethod.GET)
	public @ResponseBody Optional<Task> findTaskRest(@PathVariable("id") Long taskId) {
		return repository.findById(taskId);
	}

	// RESTful service to get person by id
	@RequestMapping(value = "/person/{pid}", method = RequestMethod.GET)
	public @ResponseBody Optional<Task> findPersonRest(@PathVariable("pid") Long personId) {
		return repository.findById(personId);
	}

	// add task
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/add")
	public String addTask(Model model) {
		model.addAttribute("task", new Task());
		model.addAttribute("persons", prepository.findAll());
		return "addtask";
	}

	// add person task
	@RequestMapping(value = "/padd")
	public String paddTask(Model model) {
		model.addAttribute("task", new Task());
		model.addAttribute("persons", prepository.findAll());
		return "personaddtask";
	}

	// save
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Task task) {
		// tallennetaan tehtävä ja otetaan viite tallennettuun olioon haltuun
		repository.save(task);
		// ohjataan pyyntö sopivaan näkymään
		return "redirect:tasklist";
	}

	// save person task
	@RequestMapping(value = "/psave", method = RequestMethod.POST)
	public String psave(Task task) {
		// tallennetaan tehtävä ja otetaan viite tallennettuun olioon haltuun
		repository.save(task);
		// ohjataan pyyntö sopivaan näkymään
		return "redirect:tasklist";
	}

	// Edit task
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/edit/{id}")
	public String editTask(@PathVariable("id") Long taskId, Model model) {
		model.addAttribute("task", repository.findById(taskId));
		model.addAttribute("persons", prepository.findAll());
		return "edittask";
	}

	// Edit person task
	@RequestMapping(value = "/pedit/{id}")
	public String personEditTask(@PathVariable("id") Long id, Model model) {

		model.addAttribute("person", prepository.findById(id));
		model.addAttribute("task", repository.findById(id));

		model.addAttribute("persons", prepository.findAll());
		return "personedittask";
	}

	// delete task
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deleteTask(@PathVariable("id") Long taskId, Model model) {
		repository.deleteById(taskId);
		return "redirect:../tasklist";
	}

	// delete person task
	@RequestMapping(value = "/pdelete/{id}", method = RequestMethod.GET)
	public String personDeleteTask(@PathVariable("id") Long taskId, Model model) {
		repository.deleteById(taskId);
		return "redirect:../tasklist";
	}
}
