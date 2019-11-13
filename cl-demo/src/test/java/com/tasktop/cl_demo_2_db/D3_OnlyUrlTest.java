package com.tasktop.cl_demo_2_db;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.jupiter.api.Test;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
public class D3_OnlyUrlTest {

	@Test
	void pingPostgres() throws Exception {
		Connection con = newConnection("jdbc:tc:postgresql:9.6.8:///databasename");
		con.createStatement().execute("SELECT 1");
	}

	
//	@Test
//	void pingPostgresWithInitFunction() throws Exception {
//		Connection con = newConnection("jdbc:tc:postgresql:9.6.8:///databasename?TC_INITFUNCTION=MyTestClass::initDatabase");
//		con.createStatement().execute("SELECT 1");
//	}
//
//	// called by testcontainers
//	public static void initDatabase(Connection con) {
//		System.err.println(con);
//	}
	
	private Connection newConnection(String jdbcUrl) {
		try {
			Class.forName("org.postgresql.Driver");
			return DriverManager.getConnection(jdbcUrl);
		} catch (Exception e) {
			throw new AssertionError(e);
		}
	}
}
