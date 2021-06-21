package br.com.rangood.pdv.rangoodpdvservicediscovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class RangoodPdvServiceDiscoveryApplication {

	public static void main(String[] args) {
		SpringApplication.run(RangoodPdvServiceDiscoveryApplication.class, args);
	}

}
