package puf.m2.basket.model.entity;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;

import puf.m2.basket.db.entity.DbTeams;
import puf.m2.basket.model.support.BasketException;

public class CategoryTest {

    @Test
    public void testSave() throws SQLException, BasketException {
        Category cat = new Category();
        cat.setId(123);
        cat.setCategoryName("asd");
        
        for (Team team : Team.loadAll()) {
            cat.addTeam(team);
        }

        cat.save();
    }

}
