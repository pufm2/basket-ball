package puf.m2.basket.db.entity;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.REF;
import oracle.sql.STRUCT;

public class SdoPointTypeRef implements ORAData, ORADataFactory {
    public static final String _SQL_BASETYPE = "MDSYS.SDO_POINT_TYPE";
    public static final int _SQL_TYPECODE = OracleTypes.REF;

    REF _ref;

    private static final SdoPointTypeRef _SdoPointTypeRefFactory = new SdoPointTypeRef();

    public static ORADataFactory getORADataFactory() {
        return _SdoPointTypeRefFactory;
    }

    /* constructor */
    public SdoPointTypeRef() {
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
        SdoPointTypeRef r = new SdoPointTypeRef();
        r._ref = (REF) d;
        return r;
    }

    public static SdoPointTypeRef cast(ORAData o) throws SQLException {
        if (o == null)
            return null;
        try {
            return (SdoPointTypeRef) getORADataFactory().create(
                    o.toDatum(null), OracleTypes.REF);
        } catch (Exception exn) {
            throw new SQLException("Unable to convert "
                    + o.getClass().getName() + " to SdoPointTypeRef: "
                    + exn.toString());
        }
    }

    public SdoPointType getValue() throws SQLException {
        return (SdoPointType) SdoPointType.getORADataFactory().create(
                _ref.getSTRUCT(), OracleTypes.REF);
    }

    public void setValue(SdoPointType c) throws SQLException {
        _ref.setValue(c.toDatum(_ref.getJavaSqlConnection()));
    }
}
