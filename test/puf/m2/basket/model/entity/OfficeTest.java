package puf.m2.basket.model.entity;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;
    
import puf.m2.basket.db.entity.SdoGeometry;
import puf.m2.basket.model.entity.ref.OfficeRef;
import puf.m2.basket.model.support.BasketException;
import puf.m2.basket.model.support.EntityUtils;

public class OfficeTest {

    @Test
    public void testLoad() throws BasketException, SQLException {
        Office o1 = EntityUtils.loadById(1, Office.class);
        Office o2 = EntityUtils.loadById(2, Office.class);
        assertNotNull(o1.getRef(OfficeRef.class));
        
        System.out.println(o1.distance(o2));
        System.out.println(o2.distance(o1));
    }
    
    @Test
    public void testSave() throws SQLException, BasketException {
        
        Office o = new Office();
        o.setId(100);
        
        Address a = new Address("1", "St No.1", "Dist No.1", "Nha Trang city");
        SdoGeometry loc = new SdoGeometry(12.254128, 109.199867);

        o.setOfficeAddress(a);
        o.setOfficeName("123");
        o.setLoc(loc);
        o.setDeleted(0);
        
        o.save();

        Office o1 = EntityUtils.loadById(1, Office.class);
        System.out.println(o1.distance(o));
        
    }

    @Test
    public void testUpdate() throws BasketException, SQLException {
        Office o = EntityUtils.loadById(1, Office.class);
        o.setOfficeName("OFFICE 1 - 1");
        
        o.update();
    }

}
