package com.rr.plataformaead;

import org.springframework.boot.SpringApplication;

public class TestPlataformaeadApplication {

	public static void main(String[] args) {
		SpringApplication.from(PlataformaeadApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
