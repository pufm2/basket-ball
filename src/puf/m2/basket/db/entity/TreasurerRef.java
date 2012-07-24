package puf.m2.basket.db.entity;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.REF;
import puf.m2.basket.model.entity.Treasurer;

public class TreasurerRef implements ORAData, ORADataFactory {
    public static final String _SQL_BASETYPE = "BASKET_USER.T_TREASURER";
    public static final int _SQL_TYPECODE = OracleTypes.REF;

    REF _ref;

    private static final TreasurerRef _TreasurerRefFactory = new TreasurerRef();

    public static ORADataFactory getORADataFactory() {
        return _TreasurerRefFactory;
    }

    /* constructor */
    public TreasurerRef() {
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
        TreasurerRef r = new TreasurerRef();
        r._ref = (REF) d;
        return r;
    }

    public static TreasurerRef cast(ORAData o) throws SQLException {
        if (o == null)
            return null;
        try {
            return (TreasurerRef) getORADataFactory().create(o.toDatum(null),
                    OracleTypes.REF);
        } catch (Exception exn) {
            throw new SQLException("Unable to convert "
                    + o.getClass().getName() + " to TreasurerRef: "
                    + exn.toString());
        }
    }

    public Treasurer getValue() throws SQLException {
        return (Treasurer) Treasurer.getORADataFactory().create(
                _ref.getSTRUCT(), OracleTypes.REF);
    }

    public void setValue(Treasurer c) throws SQLException {
        _ref.setValue(c.toDatum(_ref.getJavaSqlConnection()));
    }
}
