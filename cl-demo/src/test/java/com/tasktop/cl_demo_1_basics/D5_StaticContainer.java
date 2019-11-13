package com.tasktop.cl_demo_1_basics;

import static com.tasktop.cl_demo_99_other.Requester.doGet;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
public class D5_StaticContainer {

	@Container
	private final GenericContainer<?> myWebserver = new GenericContainer<>("jetty") //
			.withExposedPorts(8080);

	@ParameterizedTest
	@MethodSource("iterations")
	void pingServer(Integer iteration) throws Exception {
		doGet("http://" + myWebserver.getContainerIpAddress() + ":" + myWebserver.getFirstMappedPort());
	}

	static Stream<Integer> iterations() {
		return IntStream.range(1, 100).boxed();
	}

}
