package puf.m2.basket.model.entity;

import java.sql.SQLException;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import puf.m2.basket.db.entity.DbVicePresident;

public class VicePresident extends DbVicePresident implements ORAData,
        ORADataFactory {
    
    public static final String TABLE_NAME = "vice_president";
    
    private static final VicePresident _VicePresidentFactory = new VicePresident();

    public static ORADataFactory getORADataFactory() {
        return _VicePresidentFactory;
    }

    public VicePresident() {
        super();
    }

    public VicePresident(Integer id, String personName) throws SQLException {
        setId(id);
        setPersonName(personName);
    }

    /* ORAData interface */
    @Override
    protected ORAData createExact(Datum d, int sqlType) throws SQLException {
        return create(new VicePresident(), d, sqlType);
    }

}
