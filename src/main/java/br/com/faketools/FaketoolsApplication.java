package br.com.faketools;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = { "br.com.faketools" })
@EntityScan(basePackages = { "br.com.faketools.entity" })
@SpringBootApplication
public class FaketoolsApplication {

	public static void main(String[] args) {
		SpringApplication.run(FaketoolsApplication.class, args);
	}

}
