package com.example;

import com.example.ddd.domain.contracts.ActorRepository;
import com.example.ddd.domain.entities.Actor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}


	@Autowired
	private ActorRepository actorRepository;

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Aplicación arrancada");
		List<Actor> actors = actorRepository.findAll();

		System.out.println(actors);
	}

}
