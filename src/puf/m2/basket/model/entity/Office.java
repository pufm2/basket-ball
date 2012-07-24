package puf.m2.basket.model.entity;

import java.sql.SQLException;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import puf.m2.basket.db.entity.DbOffice;
import puf.m2.basket.db.entity.OfficeRef;
import puf.m2.basket.model.support.BasketException;

public class Office extends DbOffice implements ORAData, ORADataFactory {
    
    public static final String TABLE_NAME = "office";
            
    private static final Office _OfficeFactory = new Office();
    
    private OfficeRef ref;

    public static ORADataFactory getORADataFactory() {
        return _OfficeFactory;
    }

    public Office() {
        super();
    }

    public Office(Integer id, String officeName, Address officeAddress)
            throws SQLException {
        setId(id);
        setOfficeName(officeName);
        setOfficeAddress(officeAddress);
    }

    /* ORAData interface */
    @Override
    public ORAData create(Datum d, int sqlType) throws SQLException {
        return create(new Office(), d, sqlType);
    }
    
    public OfficeRef getRef() throws BasketException {
        if (ref == null) {
            ref = getRef(OfficeRef.class);
        }
        return ref;
    }

}
