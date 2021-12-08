package kr.pe.playdata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import kr.pe.playdata.service.BoardService;

@SpringBootApplication
@ComponentScan(basePackages = "kr.pe.playdata.config.security, kr.pe.playdata.service" , basePackageClasses = BoardService.class)
public class BlackAndWhiteApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlackAndWhiteApplication.class, args);
		
	}

}