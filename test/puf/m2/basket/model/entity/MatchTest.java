package puf.m2.basket.model.entity;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.sql.Timestamp;

import org.junit.Test;

import puf.m2.basket.model.support.BasketException;
import puf.m2.basket.model.support.EntityUtils;

public class MatchTest {

    @Test
    public void testSave() throws SQLException, BasketException {
        Match m = new Match(111, new Timestamp(System.currentTimeMillis()), null, null, 0);
        Season s = EntityUtils.loadById(1, Season.class);
       // m.setSeason(s.getRef());
        
        Player p = Player.loadById(1);
        Team t = Team.loadById(1);
        m.addScoreDetail(new ScoreDetail(456, t.getRef(), p.getRef(), 999));
        m.save();
    }
    
    @Test
    public void testUpdate() throws SQLException, BasketException {
        Season s = EntityUtils.loadById(3, Season.class);
        Match match = EntityUtils.loadById(1, Match.class);
       // match.setSeason(s.getRef());
        match.update();
    }

}
