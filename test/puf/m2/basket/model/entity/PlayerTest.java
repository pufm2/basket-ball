package puf.m2.basket.model.entity;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import puf.m2.basket.model.support.BasketException;
import static org.junit.Assert.*;

public class PlayerTest {
    @Test
    public void testLoadAll() throws BasketException, SQLException {
        List<Player> players = Player.loadAll();
        
        for (Player p : players) {
            System.out.println(p.getId());
        }
        
        assertTrue(players.size() > 0);
    }
    
    @Test
    public void testLoadById() throws BasketException, SQLException {
        Player player = Player.loadById(1);
        System.out.println(player.getId());
        
        assertNotNull(player);
        
    }
    
    @Test
    public void testSave() throws SQLException, BasketException {
        Player player = new Player(99, "a", "123", new Timestamp(345345345), new Address(), null);
        player.addTeam(Team.loadById(1));
        player.addTeam(Team.loadById(2));
        
        player.save();
        
    }
    
    @Test
    public void testUpdate() throws SQLException, BasketException {
        Player player = Player.loadById(99);
        player.update();
    }
}
