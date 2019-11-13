package com.tasktop.cl_demo_3_advanced;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.testcontainers.containers.RabbitMQContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

@Testcontainers
public class D6_RabbitTest {

	private static final String QUEUE_NAME = "containerconf";
	
	@Container
	private final RabbitMQContainer rabbitContainer = new RabbitMQContainer();

	@Test
	void ping() throws Exception {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setUri(rabbitContainer.getAmqpUrl());
		try (Connection connection = factory.newConnection(); Channel channel = connection.createChannel()) {
			channel.queueDeclare(QUEUE_NAME, false, false, false, null);
			installMessageRetriever(channel);
			channel.basicPublish("", QUEUE_NAME, null, "Hallo".getBytes());
			sitAndWait();
		}
	}

	private void installMessageRetriever(Channel channel) throws IOException {
		DeliverCallback deliverCallback = (consumerTag, delivery) -> {
			String message = new String(delivery.getBody(), "UTF-8");
			System.err.println("📦 Received '" + message + "'");
			testWaiter.countDown();
		};
		channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> {
		});
	}

	private CountDownLatch testWaiter = new CountDownLatch(1);

	private void sitAndWait() throws InterruptedException {
		testWaiter.await(1, TimeUnit.MINUTES);
	}

}
