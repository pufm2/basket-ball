package puf.m2.basket.db;

import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLWarning;

import oracle.jdbc.pool.OracleDataSource;
import puf.m2.basket.utils.PropertyUtils;

public class JDBCUtil {

    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<Connection>();

    public static Connection getConnection() throws SQLException {

        if (threadLocal.get() != null) {
            return threadLocal.get();
        } else {
            OracleDataSource ods = new OracleDataSource();

            ods.setDriverType("thin");
            ods.setServerName(PropertyUtils.props.getProperty("server"));

            ods.setNetworkProtocol("tcp");

            String db = PropertyUtils.props.getProperty("db");
            ods.setDatabaseName(db);
            if ("ora92".equals(db)) {
                ods.setPortNumber(1522);
            } else {
                ods.setPortNumber(Integer.parseInt(PropertyUtils.props.getProperty("port")));
            }
            ods.setUser(PropertyUtils.props.getProperty("user"));
            ods.setPassword(PropertyUtils.props.getProperty("password"));
            System.out.println("URL:" + ods.getURL());
            System.out.flush();

            Connection connection = ods.getConnection();
            connection.setAutoCommit(false);
            
            threadLocal.set(connection);
            return connection;
        }
    }

    public static void close(ResultSet resultSet, Statement statement,
            Connection connection) {
        try {
            if (resultSet != null)
                resultSet.close();
            if (statement != null)
                statement.close();
            if (connection != null)
                connection.close();
        } catch (SQLException ignored) {
        }

    }

    public static void close(ResultSet resultSet, Statement statement) {
        try {
            if (resultSet != null)
                resultSet.close();
            if (statement != null)
                statement.close();
        } catch (SQLException ignored) {
        }
    }

    public static void close(ResultSet resultSet) {
        try {
            if (resultSet != null)
                resultSet.close();
        } catch (SQLException ignored) {
        }

    }

    public static void close(Statement statement) {
        try {
            if (statement != null)
                statement.close();
        } catch (SQLException ignored) {
        }

    }

    public static void close(Connection connection) {
        try {
            if (connection != null)
                connection.close();
        } catch (SQLException ignored) {
        }
    }

    public static void printException(Exception e) {
        System.out.println("Exception caught! Exiting ..");
        System.out.println("error message: " + e.getMessage());
        e.printStackTrace();
    }

    public static void printExceptionAndRollback(Connection conn, Exception e) {
        printException(e);
        try {
            if (conn != null)
                conn.rollback();
        } catch (SQLException ignore) {
        }
    }

    public static void startTrace(Connection connection) throws SQLException {
        String setTimedStatisticsStmt = "alter session set timed_statistics=true";
        String setTraceStmt = "alter session set events '10046 trace name context forever, level 12'";
        Statement stmt = null;
        try {
            stmt = connection.createStatement();
            stmt.execute(setTimedStatisticsStmt);
            stmt.execute(setTraceStmt);
        } finally {
            stmt.close();
        }
    }

    public static void printRsetTypeAndConcurrencyType(Statement stmt)
            throws SQLException {
        System.out.print("\tResult set category (using Statement API): ");
        int resultSetType = stmt.getResultSetType();
        switch (resultSetType) {
        case ResultSet.TYPE_FORWARD_ONLY:
            System.out.print("Forward only");
            break;
        case ResultSet.TYPE_SCROLL_INSENSITIVE:
            System.out.print("Scroll insensitive");
            break;
        case ResultSet.TYPE_SCROLL_SENSITIVE:
            System.out.print("Scroll sensitive");
            break;
        }
        int resultSetConcurrency = stmt.getResultSetConcurrency();
        switch (resultSetConcurrency) {
        case ResultSet.CONCUR_READ_ONLY:
            System.out.println(", Read only");
            break;
        case ResultSet.CONCUR_UPDATABLE:
            System.out.println(", Updatable");
            break;
        }
    }

    public static void printRsetTypeAndConcurrencyType(ResultSet rset)
            throws SQLException {
        int resultSetType = rset.getType();
        System.out.print("\tResult set category (using ResultSet API): ");

        switch (resultSetType) {
        case ResultSet.TYPE_FORWARD_ONLY:
            System.out.print("Forward only");
            break;
        case ResultSet.TYPE_SCROLL_INSENSITIVE:
            System.out.print("Scroll insensitive");
            break;
        case ResultSet.TYPE_SCROLL_SENSITIVE:
            System.out.print("Scroll sensitive");
            break;
        }
        int resultSetConcurrency = rset.getConcurrency();
        switch (resultSetConcurrency) {
        case ResultSet.CONCUR_READ_ONLY:
            System.out.println(", Read only");
            break;
        case ResultSet.CONCUR_UPDATABLE:
            System.out.println(", Updatable");
            break;
        }
    }

    public static void printWarnings(ResultSet resultSet) throws SQLException {
        System.out.println("Resultset warnings begin:");
        if (resultSet != null) {
            SQLWarning warning = resultSet.getWarnings();
            if (warning != null)
                warning = warning.getNextWarning();
            if (warning != null)
                System.out.println("Message: " + warning.getMessage());
        }
        System.out.println("Resultset warnings end");
    }

    public static void printWarnings(Statement stmt) throws SQLException {
        System.out.println("Resultset warnings begin:");
        if (stmt != null) {
            SQLWarning warning = stmt.getWarnings();
            if (warning != null)
                warning = warning.getNextWarning();
            if (warning != null)
                System.out.println("Message: " + warning.getMessage());
        }
        System.out.println("Resultset warnings end");
    }

    public static void setSessionCachedCursors(Connection connection,
            int sessionCachedCursors) throws SQLException {
        String stmtStr = "alter session set session_cached_cursors="
                + sessionCachedCursors;
        Statement stmt = null;
        try {
            stmt = connection.createStatement();
            stmt.execute(stmtStr);
        } finally {
            stmt.close();
        }
    }
}
