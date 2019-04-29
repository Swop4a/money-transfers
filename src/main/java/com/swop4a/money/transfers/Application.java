package com.swop4a.money.transfers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author swop4a
 * @since 28/04/2019 12:55
 */
@Controller
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@GetMapping("/swagger")
	public String swagger() {
		return "redirect:swagger-ui.html";
	}
}
