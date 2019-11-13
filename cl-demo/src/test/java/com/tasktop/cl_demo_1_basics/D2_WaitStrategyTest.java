package com.tasktop.cl_demo_1_basics;

import static com.tasktop.cl_demo_99_other.Requester.doGet;

import org.junit.jupiter.api.Test;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
public class D2_WaitStrategyTest {

	@Container
	private final GenericContainer<?> myWebserver = new GenericContainer<>("jetty") //
			.withExposedPorts(8080) //
			.waitingFor(
					Wait.forHttp("/") //
					.forStatusCode(404)
					.forResponsePredicate(r -> r.contains("Powered by Eclipse Jetty")));
//			.waitingFor(Wait.forLogMessage(".*ContainerConf started.*\\n", 1));

	@Test
	void testName() throws Exception {
		doGet("http://" + myWebserver.getContainerIpAddress() + ":" + myWebserver.getFirstMappedPort());
	}

}
