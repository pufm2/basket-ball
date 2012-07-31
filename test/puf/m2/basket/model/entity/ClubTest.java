package puf.m2.basket.model.entity;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;

import puf.m2.basket.model.entity.ref.OfficeRef;
import puf.m2.basket.model.entity.ref.SecretaryRef;
import puf.m2.basket.model.entity.ref.TreasurerRef;
import puf.m2.basket.model.entity.ref.VicePresidentRef;
import puf.m2.basket.model.support.BasketException;
import puf.m2.basket.model.support.EntityUtils;

public class ClubTest {

    @Test
    public void testSave() throws SQLException, BasketException {
        Club c = new Club();
        c.setId(100);
        c.setClubName("barcelona");
        c.setClubOffice(EntityUtils.loadById(1, Office.class).getRef(OfficeRef.class));
        c.setClubPresident(EntityUtils.loadById(1, President.class).getRef());
        c.setClubSecretary(EntityUtils.loadById(1, Secretary.class).getRef(SecretaryRef.class));
        c.setClubTreasurer(EntityUtils.loadById(1, Treasurer.class).getRef(TreasurerRef.class));
        c.setClubVicePresident(EntityUtils.loadById(1, VicePresident.class).getRef(VicePresidentRef.class));
        c.save();
        
    }
    
    @Test
    public void testUpdate() throws SQLException, BasketException {
        Club c = EntityUtils.loadById(1, Club.class);
        c.setClubName("Club 1 - 1");
        President p = EntityUtils.loadById(3, President.class);
        c.setClubPresident(p.getRef());
        
        c.update();
    }

}
