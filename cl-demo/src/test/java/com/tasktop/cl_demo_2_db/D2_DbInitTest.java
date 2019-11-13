package com.tasktop.cl_demo_2_db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;

import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
public class D2_DbInitTest {

	@Container
	private final PostgreSQLContainer<?> postgresContainer = new PostgreSQLContainer<>()
			.withInitScript("com/tasktop/cl_demo_2_db/init.sql");

	@Test
	void pingPostgres() throws Exception {
		Connection con = newConnection();
		queryTableContent(con, "SELECT * FROM containerconf");
	}

	private void queryTableContent(Connection con, String sql) throws SQLException {
		Statement stmt = con.createStatement();
		stmt.execute(sql);
		ResultSet rs = stmt.getResultSet();
		rs.next();
		System.out.println(MessageFormat.format("- {0} ({1})", rs.getString(1), rs.getString(2)));
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
