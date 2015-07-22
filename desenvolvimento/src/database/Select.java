package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class Select {
	
	protected static List<String> selectListOf(String sql) {
		
		if (sql.endsWith(";")) {
			sql = sql.substring(0, sql.length() - 1);
		}

		Connection connection = null;

		LinkedList<String> lista = new LinkedList<String>();

		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {

			connection = ConnectionDB.getConnection();

			stmt = connection.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				lista.addLast(rs.getString(1));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			ConnectionDB.close(rs);
			ConnectionDB.close(stmt);
			ConnectionDB.close(connection);

		}

		return lista;
	}

	protected static String[][] getMatrizOf(String sql) {
		
		if (sql.endsWith(";")) {
			sql = sql.substring(0, sql.length() - 1);
		}

		Connection connection = null;

		LinkedList<String[]> lista = new LinkedList<String[]>();

		PreparedStatement stmt = null;

		ResultSet rs = null;

		try {

			connection = ConnectionDB.getConnection();

			stmt = connection.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				ResultSetMetaData rsmd = rs.getMetaData();
				int numeroColunas = rsmd.getColumnCount();
				String[] registro = new String[numeroColunas];
				for (int i = 0; i < registro.length; i++) {
					registro[i] = rs.getString(i + 1);
				}

				lista.addLast(registro);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			ConnectionDB.close(rs);
			ConnectionDB.close(stmt);
			ConnectionDB.close(connection);

		}

		return lista.toArray(new String[lista.size()][]);
	}

	protected static String getValor(String sql) {
		Connection connection = null;

		String sRet = null;

		PreparedStatement stmt = null;

		ResultSet rs = null;

		try {

			connection = ConnectionDB.getConnection();

			stmt = connection.prepareStatement(sql);
			rs = stmt.executeQuery();

			rs.next();
			sRet = rs.getString(1);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			ConnectionDB.close(rs);
			ConnectionDB.close(stmt);
			ConnectionDB.close(connection);

		}

		return sRet;
	}

}
