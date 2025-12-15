package com.example.demo;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

// import io.opentelemetry.instrumentation.annotations.*;

@SpringBootApplication
@Controller
public class DemoApplication {

	@Autowired
	TodoRepository todoRepository;

	@GetMapping
	public String getItems(Model model) {
		var todos = todoRepository.findAll().stream().map(Todo::getTodo).toList();
		model.addAttribute("items", todos);
		return "items";
	}

	@PostMapping
	public String addItem(String todo) {
		this.dummyBusinessLogic(todo);
		return "redirect:/";
	}

	@PostMapping("{todo}")
	public String setItemDone(@PathVariable String todo) {
		todoRepository.deleteById(todo);
		return "redirect:/";
	}

	String dummyBusinessLogic(String todo) {
		if (todo.equals("slow")) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}

		if (todo.equals("fail")) {
			throw new RuntimeException();
		}

		todoRepository.save(new Todo(todo));

		return todo;
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}

@Entity
class Todo {

	@Id
	String todo;

	public Todo() {
	}

	public Todo(String todo) {
		this.todo = todo;
	}

	public String getTodo() {
		return this.todo;
	}

	public void setTodo(String todo) {
		this.todo = todo;
	}
}

interface TodoRepository extends ListCrudRepository<Todo, String> {
}
