package database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class Insert {
	
	protected static void doit(List<String> sqls) {

		Connection connection = null;
		Statement stmt = null;

		try {

			connection = ConnectionDB.getConnection();
			connection.setAutoCommit(false);

			stmt = connection.createStatement();

			for (String sql : sqls) {

				if (sql.endsWith(";")) {
					sql = sql.substring(0, sql.length() - 1);
				}

				stmt.addBatch(sql);

			}

			stmt.executeBatch();
			connection.commit();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			ConnectionDB.close(stmt);
			ConnectionDB.close(connection);

		}

	}

}
