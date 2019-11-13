package com.tasktop.cl_demo_2_db;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
public class D1_DbBasicsTest {

	@Container
	private final PostgreSQLContainer<?> postgresContainer = new PostgreSQLContainer<>();

	@Test
	void pingPostgres() throws Exception {
		Connection con = newConnection();
		con.createStatement().execute("SELECT 1");
	}

	private Connection newConnection() {
		try {
			Class.forName("org.postgresql.Driver");
			return DriverManager.getConnection(postgresContainer.getJdbcUrl(), postgresContainer.getUsername(),
					postgresContainer.getPassword());
		} catch (Exception e) {
			throw new AssertionError(e);
		}
	}
}
