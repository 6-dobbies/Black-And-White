package kr.pe.playdata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "kr.pe.playdata.config.security" )
public class BlackAndWhiteApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlackAndWhiteApplication.class, args);
	}

}