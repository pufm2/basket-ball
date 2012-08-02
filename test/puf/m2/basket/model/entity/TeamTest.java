package puf.m2.basket.model.entity;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;

import puf.m2.basket.model.support.BasketException;

public class TeamTest {

    @Test
    public void testGetRef() throws BasketException, SQLException {
    	Team team = Team.loadById(1);
        System.out.println(team.getTeamName());
        assertNotNull(team.getRef());
    }

    @Test
    public void testSave() throws SQLException, BasketException {
    	Team team = new Team(88, "a x y", 0);
        team.save();
        
        team.setTeamName("befica");
        team.update();
    }
    
    @Test
    public void testUpdate() throws SQLException, BasketException {
    	Team team = Team.loadById(1);
        team.setTeamName("chelsea");
        team.update();
    }
}
