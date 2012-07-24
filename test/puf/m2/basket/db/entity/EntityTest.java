package puf.m2.basket.db.entity;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import puf.m2.basket.model.entity.Team;
import puf.m2.basket.model.support.BasketException;

public class EntityTest {

    @Test
    public void testGetter2FieldName() {
        assertEquals("THIS_IS_A_FILED", Entity.getter2FieldName("getThisIsAFiled"));
    }
    
    @Test
    public void testGetDbProps() throws BasketException {
        Team t = new Team();
        Map<String, Object> dbProps = new HashMap<String, Object>();
        t.getDbProps(dbProps, Team.class);
        
    }

}
