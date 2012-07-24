package puf.m2.basket.model.entity;

import java.sql.SQLException;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import puf.m2.basket.db.entity.DbAddress;

public class Address extends DbAddress implements ORAData, ORADataFactory {
    private static final Address _AddressFactory = new Address();

    public static ORADataFactory getORADataFactory() {
        return _AddressFactory;
    }

    /* ORAData interface */
    @Override
    public ORAData create(Datum d, int sqlType) throws SQLException {
        return create(new Address(), d, sqlType);
    }


}
