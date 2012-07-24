package puf.m2.basket.model.entity;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;

import puf.m2.basket.model.support.BasketException;

public class CoachTest {

    @Test
    public void testSave() throws SQLException, BasketException {
        Coach coach = new Coach();
        coach.setId(321);
        coach.setPersonName("palatini");
        
        for (Team team : Team.loadAll()) {
            coach.addTeam(team);
        }
        
        coach.save();

    }

}
