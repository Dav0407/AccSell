package com.igriss.AkkSell;

import com.igriss.AkkSell.requests.RegisterRequest;
import com.igriss.AkkSell.services.AuthenticationService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

import static com.igriss.AkkSell.roles.Role.ADMIN;
import static com.igriss.AkkSell.roles.Role.MANAGER;

@SpringBootApplication
@EnableCaching
public class AkkSellApplication {

	public static void main(String[] args) {
		SpringApplication.run(AkkSellApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(
			AuthenticationService authenticationService
	){
		return args -> {
			var admin = RegisterRequest.builder()
					.firstname("Admin")
					.lastname("Admin")
					.email("admin@mail.com")
					.password("password")
					.role(ADMIN)
					.build();
			System.out.println("Admin token: " + authenticationService.register(admin).getToken());

			var manager = RegisterRequest.builder()
					.firstname("Manager")
					.lastname("Manager")
					.email("manager@mail.com")
					.password("password")
					.role(MANAGER)
					.build();
			System.out.println("Manager token: " + authenticationService.register(manager).getToken());
		};

	}
}
