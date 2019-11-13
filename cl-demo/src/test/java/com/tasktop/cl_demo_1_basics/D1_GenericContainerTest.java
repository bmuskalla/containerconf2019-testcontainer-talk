package com.tasktop.cl_demo_1_basics;

import static com.tasktop.cl_demo_99_other.Requester.doGet;

import org.junit.jupiter.api.Test;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
public class D1_GenericContainerTest {

	@Container
	private final GenericContainer<?> myWebserv = new GenericContainer<>("alpine:3.2") //
			.withExposedPorts(80) //
			.withCommand("/bin/sh", "-c",
					"while true; do echo \"HTTP/1.1 200 OK\n\nHello ContainerConf!\" | nc -l -p 80; done");

	@Test
	void testName() throws Exception {
		doGet("http://" + myWebserv.getContainerIpAddress() + ":" + myWebserv.getFirstMappedPort());
	}
	
	
}
