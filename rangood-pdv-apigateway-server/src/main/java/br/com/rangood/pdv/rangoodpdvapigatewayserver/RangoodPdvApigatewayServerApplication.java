package br.com.rangood.pdv.rangoodpdvapigatewayserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import java.util.Map;

@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy
public class RangoodPdvApigatewayServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(RangoodPdvApigatewayServerApplication.class, args);
	}

}
