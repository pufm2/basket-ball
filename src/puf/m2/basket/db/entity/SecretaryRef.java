package puf.m2.basket.db.entity;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.REF;
import puf.m2.basket.model.entity.Secretary;

public class SecretaryRef implements ORAData, ORADataFactory {
    public static final String _SQL_BASETYPE = "BASKET_USER.T_SECRETARY";
    public static final int _SQL_TYPECODE = OracleTypes.REF;

    REF _ref;

    private static final SecretaryRef _SecretaryRefFactory = new SecretaryRef();

    public static ORADataFactory getORADataFactory() {
        return _SecretaryRefFactory;
    }

    /* constructor */
    public SecretaryRef() {
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
        SecretaryRef r = new SecretaryRef();
        r._ref = (REF) d;
        return r;
    }

    public static SecretaryRef cast(ORAData o) throws SQLException {
        if (o == null)
            return null;
        try {
            return (SecretaryRef) getORADataFactory().create(o.toDatum(null),
                    OracleTypes.REF);
        } catch (Exception exn) {
            throw new SQLException("Unable to convert "
                    + o.getClass().getName() + " to SecretaryRef: "
                    + exn.toString());
        }
    }

    public Secretary getValue() throws SQLException {
        return (Secretary) Secretary.getORADataFactory().create(
                _ref.getSTRUCT(), OracleTypes.REF);
    }

    public void setValue(Secretary c) throws SQLException {
        _ref.setValue(c.toDatum(_ref.getJavaSqlConnection()));
    }
}
