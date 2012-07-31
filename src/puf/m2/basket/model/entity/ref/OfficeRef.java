package puf.m2.basket.model.entity.ref;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.REF;
import oracle.sql.STRUCT;
import puf.m2.basket.model.entity.Office;

public class OfficeRef implements ORAData, ORADataFactory {
    public static final String _SQL_BASETYPE = "BASKET_USER.T_OFFICE";
    public static final int _SQL_TYPECODE = OracleTypes.REF;

    REF _ref;

    private static final OfficeRef _OfficeRefFactory = new OfficeRef();

    public static ORADataFactory getORADataFactory() {
        return _OfficeRefFactory;
    }

    /* constructor */
    public OfficeRef() {
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
        OfficeRef r = new OfficeRef();
        r._ref = (REF) d;
        return r;
    }

    public static OfficeRef cast(ORAData o) throws SQLException {
        if (o == null)
            return null;
        try {
            return (OfficeRef) getORADataFactory().create(o.toDatum(null),
                    OracleTypes.REF);
        } catch (Exception exn) {
            throw new SQLException("Unable to convert "
                    + o.getClass().getName() + " to OfficeRef: "
                    + exn.toString());
        }
    }

    public Office getValue() throws SQLException {
        return (Office) Office.getORADataFactory().create(_ref.getSTRUCT(),
                OracleTypes.REF);
    }

    public void setValue(Office c) throws SQLException {
        _ref.setValue(c.toDatum(_ref.getJavaSqlConnection()));
    }
}
