package com.tasktop.cl_demo_3_advanced;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.jupiter.api.Test;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
public class D7_ReuseContainerTest {

	@Test
	void pingPostgres() throws Exception {
		Connection con = newConnection("jdbc:tc:postgresql:9.6.12:///?TC_REUSABLE=true");
		con.createStatement().execute("SELECT 1");
	}

	private Connection newConnection(String url) {
		try {
			Class.forName("org.postgresql.Driver");
			return DriverManager.getConnection(url);
		} catch (Exception e) {
			throw new AssertionError(e);
		}
	}
}
