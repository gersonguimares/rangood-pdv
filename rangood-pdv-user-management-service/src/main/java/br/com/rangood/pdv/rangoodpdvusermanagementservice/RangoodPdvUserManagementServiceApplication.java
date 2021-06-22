package br.com.rangood.pdv.rangoodpdvusermanagementservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class RangoodPdvUserManagementServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RangoodPdvUserManagementServiceApplication.class, args);
	}

}
