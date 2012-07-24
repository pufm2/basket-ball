package puf.m2.basket.model.entity;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;

import puf.m2.basket.db.entity.SecretaryRef;
import puf.m2.basket.db.entity.TreasurerRef;
import puf.m2.basket.db.entity.VicePresidentRef;
import puf.m2.basket.model.support.BasketException;
import puf.m2.basket.model.support.EntityUtils;

public class ClubTest {

    @Test
    public void testSave() throws SQLException, BasketException {
        Club c = new Club();
        c.setId(100);
        c.setClubName("barcelona");
        c.setClubOffice(EntityUtils.loadById(1, Office.class).getRef());
        c.setClubPresident(EntityUtils.loadById(1, President.class).getRef());
        c.setClubSecretary(EntityUtils.loadById(1, Secretary.class).getRef(SecretaryRef.class));
        c.setClubTreasurer(EntityUtils.loadById(1, Secretary.class).getRef(TreasurerRef.class));
        c.setClubVicePresident(EntityUtils.loadById(1, Secretary.class).getRef(VicePresidentRef.class));
        c.save();
        
    }

}
