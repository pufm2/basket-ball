package puf.m2.basket.model.entity;

import java.sql.SQLException;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import puf.m2.basket.db.entity.DbPresident;
import puf.m2.basket.db.entity.PresidentRef;
import puf.m2.basket.model.support.BasketException;

public class President extends DbPresident implements ORAData, ORADataFactory {
    
    public static final String TABLE_NAME = "president";
    private static final President _PresidentFactory = new President();
    
    private PresidentRef ref;

    public static ORADataFactory getORADataFactory() {
        return _PresidentFactory;
    }

    public President() {
        super();
    }

    public President(Integer id, String personName) throws SQLException {
        setId(id);
        setPersonName(personName);
    }

    /* ORAData interface */
    @Override
    protected ORAData createExact(Datum d, int sqlType) throws SQLException {
        return create(new President(), d, sqlType);
    }

    public PresidentRef getRef() throws BasketException {
        if (ref == null) {
            ref = getRef(PresidentRef.class);
        }
        return ref;
    }
}
