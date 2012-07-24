package puf.m2.basket.model.entity;

import java.sql.SQLException;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import puf.m2.basket.db.entity.DbSecretary;
import puf.m2.basket.db.entity.SecretaryRef;
import puf.m2.basket.db.entity.TeamRef;
import puf.m2.basket.model.support.BasketException;

public class Secretary extends DbSecretary implements ORAData, ORADataFactory {
    
    public static final String TABLE_NAME = "SECRETARY";

    private static final Secretary _SecretaryFactory = new Secretary();
    
    private SecretaryRef ref;

    public static ORADataFactory getORADataFactory() {
        return _SecretaryFactory;
    }

    public Secretary() {
        super();
    }

    public Secretary(Integer id, String personName) throws SQLException {
        setId(id);
        setPersonName(personName);
    }

    /* ORAData interface */
    @Override
    protected ORAData createExact(Datum d, int sqlType) throws SQLException {
        return create(new Secretary(), d, sqlType);
    }

    public SecretaryRef getRef() throws BasketException {
        if (ref == null) {
            ref = getRef(SecretaryRef.class);
        }
        return ref;
    }
}
