package puf.m2.basket.model.entity;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import puf.m2.basket.model.support.BasketException;
import puf.m2.basket.model.support.EntityUtils;

public class CoachTest {

    @Test
    public void testSave() throws SQLException, BasketException {
        Coach coach = new Coach();
        coach.setId(321);
        coach.setPersonName("palatini");
        
        List<Team> teams = EntityUtils.loadByCondition(null, Team.class, null);
        for (Team team : teams) {
            coach.addTeam(team);
        }
        
        coach.save();

    }

}
