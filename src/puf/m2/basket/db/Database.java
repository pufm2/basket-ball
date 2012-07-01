package puf.m2.basket.db;

import java.sql.ResultSet;

import puf.m2.basket.exception.DbException;

public interface Database {

	public void createConnection() throws DbException;

	public ResultSet executeQuery(String query) throws DbException;

	public int executeUpdate(String query) throws DbException;

	public void closeConnection() throws DbException;
}
