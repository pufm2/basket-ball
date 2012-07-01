package puf.m2.basket.db;

import java.util.HashMap;
import java.util.Map;

public class DatabaseFactory {

	public static final String ORACLE_DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:XE";
	private static final String USERNAME = "basket_user";
	private static final String PASSWORD = "basket_pwd";

	private static final Map<String, Database> DB_POOL;

	public static final Database DEFAULT_DB;

	static {
		DB_POOL = new HashMap<String, Database>();
		DEFAULT_DB = createDatabase(ORACLE_DRIVER, DB_URL, USERNAME, PASSWORD);
	}

	public static Database createDatabase(String jdbcDriver, String dbUrl,
			String username, String password) {
		if (DB_POOL.containsKey(dbUrl)) {
			return DB_POOL.get(dbUrl);
		}
		try {
			Class.forName(jdbcDriver);
		} catch (ClassNotFoundException e) {
			System.out.println("Could not find the database driver");
			return null;
		}

		Database db = null;
		if (ORACLE_DRIVER.equals(jdbcDriver)) {
			db = new OracleDatabase(dbUrl, username, password);
		}
		DB_POOL.put(dbUrl, db);
		return db;
	}
}
