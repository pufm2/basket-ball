package puf.m2.basket.model.entity;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import puf.m2.basket.model.support.BasketException;
import puf.m2.basket.model.support.Condition;
import puf.m2.basket.model.support.EntityUtils;

public class UserTest {

    @Test
    public void testGetId() throws BasketException {
        List<User> users = EntityUtils.loadByCondition(null, User.class, null);
    }

    @Test
    public void testGet() throws BasketException {
        List<User> users = EntityUtils.loadByCondition(new Condition("username", "HOAN").and(new Condition("password", "HOAN")),
                User.class, null);
        assertEquals(1, users.size());
    }
}
