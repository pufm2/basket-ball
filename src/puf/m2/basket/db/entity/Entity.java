package puf.m2.basket.db.entity;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import oracle.jdbc.OracleResultSet;
import oracle.sql.ORADataFactory;
import puf.m2.basket.db.JDBCUtil;
import puf.m2.basket.model.support.BasketException;
import puf.m2.basket.model.support.DbProp;

public abstract class Entity {
    public abstract Integer getId() throws SQLException;

    public <T> T getRef(Class<T> clazz) throws BasketException {

        try {
            
            Field field = getClass().getDeclaredField("ref");
            field.setAccessible(true);
            
            T ref = (T) field.get(this);
            if (ref != null) {
                return ref;
            }
            
            Connection conn = null;
            String tableName = (String) getClass().getField("TABLE_NAME").get(
                    null);
            String stmtString = "SELECT ref(t) FROM " + tableName
                    + " t where id = " + getId();
            conn = JDBCUtil.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(stmtString);
            OracleResultSet orset = (OracleResultSet) pstmt.executeQuery();

            ORADataFactory oraDataFactory = (ORADataFactory) clazz.getMethod(
                    "getORADataFactory").invoke(null);
            if (orset.next()) {
                ref = (T) orset.getORAData(1, oraDataFactory);
                field.set(this, ref);
                return ref;
            }
        } catch (Exception e) {
            throw new BasketException(e);
        } finally {
            // JDBCUtil.close(conn);
        }
        return null;
    }

    public static String getter2FieldName(String getter) {

        StringBuffer sb = new StringBuffer();
        sb.append(getter.charAt(3));
        for (int i = 4; i < getter.length(); i++) {
            char c = getter.charAt(i);
            if (Character.isUpperCase(c)) {
                sb.append('_');
            }
            sb.append(c);
        }

        return sb.toString().toUpperCase();

    }

    public void getDbProps(Map<String, Object> dbProps, Class<?> clazz)
            throws BasketException {

        for (Method m : clazz.getMethods()) {
            if (m.getAnnotation(DbProp.class) != null) {
                try {
                    dbProps.put(getter2FieldName(m.getName()), m.invoke(this));
                } catch (Exception e) {
                    throw new BasketException(e);
                }
            }
        }

        Class<?> superClass = clazz.getSuperclass();
        if (superClass != null) {
            getDbProps(dbProps, superClass);
        }

    }

    public void save() throws BasketException {

        Connection conn = null;
        try {
            String tableName = (String) getClass().getField("TABLE_NAME").get(
                    null);
            String stmt = "insert into " + tableName + " values(?)";

            conn = JDBCUtil.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(stmt);
            pstmt.setObject(1, this);
            pstmt.executeUpdate();
            conn.commit();

        } catch (Exception e) {

            throw new BasketException(e);

        } finally {

            // JDBCUtil.close(conn);
        }

    }

    public void update() throws BasketException {

        Connection conn = null;
        try {
            String tableName = (String) getClass().getField("TABLE_NAME").get(
                    null);
            StringBuffer stmt = new StringBuffer("update " + tableName
                    + " set ");

            Map<String, Object> dbProps = new HashMap<String, Object>();
            getDbProps(dbProps, getClass());

            Iterator<String> iter = dbProps.keySet().iterator();

            List<Object> params = new ArrayList<Object>();
            if (iter.hasNext()) {
                String propName = iter.next();
                stmt.append(propName + "=?");
                params.add(dbProps.get(propName));
            }

            while (iter.hasNext()) {
                String propName = iter.next();
                stmt.append(", " + propName + "=?");
                params.add(dbProps.get(propName));
            }
            stmt.append(" where id=" + getId());

            conn = JDBCUtil.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(stmt.toString());

            int i = 1;
            for (Object param : params) {
                pstmt.setObject(i++, param);
            }

            pstmt.executeUpdate();
            conn.commit();

        } catch (Exception e) {

            throw new BasketException(e);

        } finally {

            // JDBCUtil.close(conn);
        }

    }
}