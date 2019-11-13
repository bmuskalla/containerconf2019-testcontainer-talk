package com.tasktop.cl_demo_3_advanced;

import static com.tasktop.cl_demo_99_other.Requester.doGet;

import org.junit.jupiter.api.Test;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.Network;
import org.testcontainers.containers.ToxiproxyContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import eu.rekawek.toxiproxy.model.ToxicDirection;

@Testcontainers
public class D3_ChaosTest {

	public Network network = Network.newNetwork();

	@Container
	private final GenericContainer<?> myWebserver = new GenericContainer<>("alpine:3.2") //
			.withExposedPorts(80) //
			.withCommand("/bin/sh", "-c",
					"while true; do echo " + "\"HTTP/1.1 200 OK\n\nHello ContainerConf!\" | nc -l -p 80; done")
			.withNetwork(network);

	@Container
	private final ToxiproxyContainer toxiproxy = new ToxiproxyContainer().withNetwork(network);

	@Test
	void testName() throws Exception {
		ToxiproxyContainer.ContainerProxy proxy = toxiproxy.getProxy(myWebserver, 80);

		proxy.toxics().limitData("", ToxicDirection.DOWNSTREAM, 21);
		doGet("http://" + proxy.getContainerIpAddress() + ":" + proxy.getProxyPort());
	}

}
