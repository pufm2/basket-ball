package puf.m2.basket.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import puf.m2.basket.exception.DbException;

public class OracleDatabase implements Database {

	private String dbUrl;
	private String username;
	private String password;

	public Connection cnn;

	public OracleDatabase(String dbUrl, String username, String password) {
		this.dbUrl = dbUrl;
		this.username = username;
		this.password = password;
	}

	public void closeConnection() throws DbException {
		try {
			if (cnn != null && !cnn.isClosed()) {
				cnn.close();
			}
		} catch (Exception e) {
			throw new DbException(e);
		}
	}

	public void createConnection() throws DbException {
		try {
			if (cnn == null || cnn.isClosed()) {
				cnn = DriverManager.getConnection(dbUrl, username, password);
			}
		} catch (Exception e) {
			throw new DbException(e);
		}
	}

	public ResultSet executeQuery(String query) throws DbException {
		try {
			if (cnn == null || cnn.isClosed()) {
				cnn = DriverManager.getConnection(dbUrl, username, password);
			}
			Statement statement = cnn.createStatement();
			return statement.executeQuery(query);
		} catch (Exception e) {
			throw new DbException(e);
		}
	}

	public int executeUpdate(String query) throws DbException {
		try {

			Statement statement = cnn.createStatement();
			return statement.executeUpdate(query);
		} catch (Exception e) {
			throw new DbException(e);
		}
	}
}
