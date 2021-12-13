package kr.pe.playdata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import kr.pe.playdata.service.BoardService;

@SpringBootApplication
@ComponentScan(basePackages = "kr.pe.playdata.config.security, kr.pe.playdata.controller" , basePackageClasses = BoardService.class)
public class BlackAndWhiteApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlackAndWhiteApplication.class, args);
	}
	
	@Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}
