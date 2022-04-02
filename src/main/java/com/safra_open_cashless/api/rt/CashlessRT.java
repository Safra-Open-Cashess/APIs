package com.safra_open_cashless.api.rt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({ "com.safra_open_cashless.api" })
@EntityScan(basePackages = { "com.safra_open_cashless.api" })
public class CashlessRT {

	public static void main(String[] args) {
		SpringApplication.run(CashlessRT.class, args);
	}

}
