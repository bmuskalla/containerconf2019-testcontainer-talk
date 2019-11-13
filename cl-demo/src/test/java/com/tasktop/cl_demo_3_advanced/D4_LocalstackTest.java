package com.tasktop.cl_demo_3_advanced;

import static org.testcontainers.containers.localstack.LocalStackContainer.Service.S3;

import org.junit.jupiter.api.Test;
import org.testcontainers.containers.localstack.LocalStackContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

@Testcontainers
public class D4_LocalstackTest {

	@Container
	public LocalStackContainer localstack = new LocalStackContainer().withServices(S3);

	@Test
	public void accessS3Bucket() {
		AmazonS3 s3 = AmazonS3ClientBuilder.standard()
				.withEndpointConfiguration(localstack.getEndpointConfiguration(S3))
				.withCredentials(localstack.getDefaultCredentialsProvider()).build();

		s3.createBucket("foo");
		s3.putObject("foo", "bar", "baz");
	}

}