package com.tasktop.cl_demo_1_basics;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.containers.output.Slf4jLogConsumer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
public class D4_LoggingTest {

	private Logger logger = LoggerFactory.getLogger(D4_LoggingTest.class);

	@Container
	private final PostgreSQLContainer<?> postgresContainer = new PostgreSQLContainer<>();

	@Test
	void pingPostgres() throws Exception {
		postgresContainer.followOutput(new Slf4jLogConsumer(logger));
	}

}
