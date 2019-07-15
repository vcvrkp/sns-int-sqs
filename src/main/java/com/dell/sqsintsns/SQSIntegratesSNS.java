package com.dell.sqsintsns;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.aws.messaging.config.annotation.EnableSns;
import org.springframework.cloud.aws.messaging.config.annotation.EnableSqs;

@SpringBootApplication
@EnableSns
@EnableSqs
public class SQSIntegratesSNS {

	public static void main(String[] args) {
		SpringApplication.run(SQSIntegratesSNS.class, args);
	}

}
