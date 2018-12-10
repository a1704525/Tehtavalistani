package com.example.Tehtavalista.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long taskid;
	private String taskname;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date deadline = new Date();

	@DateTimeFormat(pattern = "HH:mm")
	private Date time = new Date();

	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "personid")
	private Person person;

	public Task() {
	}

	public Task(String taskname, Date deadline, Date time, Person person) {
		super();
		this.taskname = taskname;
		this.deadline = deadline;
		this.person = person;
		this.time = time;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	public Long getTaskid() {
		return taskid;
	}

	public void setTaskid(Long taskid) {
		this.taskid = taskid;
	}

	public String getTaskname() {
		return taskname;
	}

	public void setTaskname(String taskname) {
		this.taskname = taskname;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	@Override
	public String toString() {
		if (this.person != null)
			return "Task [taskid=" + taskid + ", taskname=" + taskname + ", deadline=" + deadline + ", time=" + time
					+ ", person=" + this.getPerson() + "]";
		else
			return "Task [taskid=" + taskid + ", taskname=" + taskname + ", deadline=" + deadline + ", time=" + time
					+ "]";
	}

}
