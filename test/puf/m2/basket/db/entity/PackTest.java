package puf.m2.basket.db.entity;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;

import puf.m2.basket.db.JDBCUtil;

public class PackTest {

    @Test
    public void test() throws SQLException {
        Pack p = new Pack(JDBCUtil.getConnection());
        System.out.println(p.getint());
    }

}
