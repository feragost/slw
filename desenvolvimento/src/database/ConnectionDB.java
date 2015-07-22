package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionDB {
	
	private static Connection staticConn;

	public static Connection getConnection() {

		if (staticConn == null) {
			try {
				Class.forName(ConfigDB.className);
				staticConn = DriverManager.getConnection(ConfigDB.connectionUrl, ConfigDB.login, ConfigDB.password);
			} catch (ClassNotFoundException e) {
				System.out.println("Classe não encontrada");
			} catch (SQLException e) {
				System.out.println("Problemas com o BD" + e);
			}

		}

		return staticConn;

	}

	public static void close(Statement stmt) {

		try {
			if (stmt != null) {
				stmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void close(ResultSet rs) {

		try {
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void close(Connection connection) {
		/*
		 * try {
		 * if (connection != null)
		 * connection.close();
		 * } catch (SQLException e) {
		 * e.printStackTrace();
		 * }
		 */
	}

}
