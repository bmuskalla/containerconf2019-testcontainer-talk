package com.tasktop.cl_demo_1_basics;

import static com.tasktop.cl_demo_99_other.Requester.doGet;

import org.junit.jupiter.api.Test;
import org.testcontainers.containers.BindMode;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
public class D3_FilesAndVolumesTest {

	@Container
	private final GenericContainer<?> myWebserver = new GenericContainer<>("jetty") //
			.withExposedPorts(8080).withClasspathResourceMapping("com/tasktop/cl_demo_1_basics/config.cfg",
					"/etc/config.cfg", BindMode.READ_ONLY);

	@Test
	void testName() throws Exception {
		doGet("http://" + myWebserver.getContainerIpAddress() + ":" + myWebserver.getFirstMappedPort());
	}
	
}
