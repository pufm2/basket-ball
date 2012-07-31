package puf.m2.basket.model.support;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import oracle.jdbc.OracleResultSet;
import oracle.sql.ORADataFactory;
import puf.m2.basket.db.JDBCUtil;

public class EntityUtils {

    public static <T> List<T> loadByCondition(Condition c, Class<T> clazz, String orderBy) throws BasketException {
        
        Connection conn = null;
        List<T> entityList = new ArrayList<T>();
        try {
            
            String where = "where ";
            if (c != null) {
                where += "(" + c + ") and ";
            }
            
            where += "deleted = 0";
                    
            String tableName = (String) clazz.getField("TABLE_NAME").get(null);
    
            String stmtString = "select value(t) from " + tableName + " t " + where;
            
            if (orderBy != null) {
                stmtString += " " + "order by " + orderBy;
            }
    
            PreparedStatement pstmt = null;
            OracleResultSet orset = null;
    
            ORADataFactory oraDataFactory = (ORADataFactory) clazz.getMethod("getORADataFactory").invoke(null);
            
            conn = JDBCUtil.getConnection();
            pstmt = conn.prepareStatement(stmtString);
            orset = (OracleResultSet) pstmt.executeQuery();
            while (orset.next()) {
                entityList.add((T) orset.getORAData(1, oraDataFactory));
            }
            
        } catch (Exception e) {
            throw new BasketException(e);
            
        } finally {
            //JDBCUtil.close(conn);
        }
        
        return entityList;
    }

    public static <T> T loadById(int id, Class<T> clazz) throws BasketException {
        List<T> entities = loadByCondition(new Condition("id", Integer.toString(id)), clazz, null);
        
        if (entities.size() > 0) {
            return entities.get(0);
        } else {
            return null;
        }
    }

}
