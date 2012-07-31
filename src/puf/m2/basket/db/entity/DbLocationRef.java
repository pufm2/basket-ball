package puf.m2.basket.db.entity;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.REF;
import oracle.sql.STRUCT;

public class DbLocationRef implements ORAData, ORADataFactory {
    public static final String _SQL_BASETYPE = "BASKET_USER.T_LOCATION";
    public static final int _SQL_TYPECODE = OracleTypes.REF;

    REF _ref;

    private static final DbLocationRef _DbLocationRefFactory = new DbLocationRef();

    public static ORADataFactory getORADataFactory() {
        return _DbLocationRefFactory;
    }

    /* constructor */
    public DbLocationRef() {
    }

    /* ORAData interface */
    @Override
    public Datum toDatum(Connection c) throws SQLException {
        return _ref;
    }

    /* ORADataFactory interface */
    @Override
    public ORAData create(Datum d, int sqlType) throws SQLException {
        if (d == null)
            return null;
        DbLocationRef r = new DbLocationRef();
        r._ref = (REF) d;
        return r;
    }

    public static DbLocationRef cast(ORAData o) throws SQLException {
        if (o == null)
            return null;
        try {
            return (DbLocationRef) getORADataFactory().create(o.toDatum(null),
                    OracleTypes.REF);
        } catch (Exception exn) {
            throw new SQLException("Unable to convert "
                    + o.getClass().getName() + " to DbLocationRef: "
                    + exn.toString());
        }
    }

    public DbLocation getValue() throws SQLException {
        return (DbLocation) DbLocation.getORADataFactory().create(
                _ref.getSTRUCT(), OracleTypes.REF);
    }

    public void setValue(DbLocation c) throws SQLException {
        _ref.setValue(c.toDatum(_ref.getJavaSqlConnection()));
    }
}
