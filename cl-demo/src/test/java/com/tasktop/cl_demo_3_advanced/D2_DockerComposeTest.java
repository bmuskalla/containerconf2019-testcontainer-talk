package com.tasktop.cl_demo_3_advanced;

import java.io.File;

import org.junit.jupiter.api.Test;
import org.testcontainers.containers.DockerComposeContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
public class D2_DockerComposeTest {

	@Container
	public static DockerComposeContainer<?> environment = new DockerComposeContainer<>(
			new File("src/test/resources/compose-test.yml")) //
					.withPull(false) //
					.withExposedService("web_1", 8080)
					.withExposedService("db_1", 5432);
					//.waitingFor(serviceName, waitStrategy);
					

	@Test
	public void accessWeb() {
		String mappedHost = environment.getServiceHost("web_1", 8080);
		Integer mappedPort = environment.getServicePort("web_1", 8080);
		String url = "http://" + mappedHost + ":" + mappedPort;
		System.err.println(url);
	}

}
