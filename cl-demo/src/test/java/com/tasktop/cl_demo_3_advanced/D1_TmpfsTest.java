package com.tasktop.cl_demo_3_advanced;

import java.util.Collections;

import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
public class D1_TmpfsTest {

	@Container
	private final GenericContainer<?> myWebserver = new GenericContainer<>("jetty") //
			.withExposedPorts(8080)
			.withTmpFs(Collections.singletonMap("/tmp", "rw,noexec,nosuid,size=50m"));

}
