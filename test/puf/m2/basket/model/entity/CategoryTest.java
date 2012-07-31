package puf.m2.basket.model.entity;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import puf.m2.basket.model.support.BasketException;
import puf.m2.basket.model.support.EntityUtils;

public class CategoryTest {

    @Test
    public void testSave() throws SQLException, BasketException {
        Category cat = new Category();
        cat.setId(123);
        cat.setCategoryName("asd");
        
        List<Team> teams = EntityUtils.loadByCondition(null, Team.class, null);
        for (Team team : teams) {
            cat.addTeam(team);
        }

        cat.save();
    }

}
