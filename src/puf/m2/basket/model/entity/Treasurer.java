package puf.m2.basket.model.entity;

import java.sql.SQLException;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import puf.m2.basket.db.entity.DbTreasurer;

public class Treasurer extends DbTreasurer implements ORAData, ORADataFactory {
    
    public static final String TABLE_NAME = "Treasurer";
    private static final Treasurer _TreasurerFactory = new Treasurer();

    public static ORADataFactory getORADataFactory() {
        return _TreasurerFactory;
    }

    public Treasurer() {
        super();
    }

    public Treasurer(Integer id, String personName) throws SQLException {
        setId(id);
        setPersonName(personName);
    }

    /* ORAData interface */
    @Override
    protected ORAData createExact(Datum d, int sqlType) throws SQLException {
        return create(new Treasurer(), d, sqlType);
    }

}
