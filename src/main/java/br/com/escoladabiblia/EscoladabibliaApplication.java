package br.com.escoladabiblia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
@EnableGlobalMethodSecurity
public class EscoladabibliaApplication {

	public static void main(String[] args) {
		SpringApplication.run(EscoladabibliaApplication.class, args);
	}

}
