package puf.m2.basket.db.entity;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.REF;
import oracle.sql.STRUCT;

public class SdoGeometryRef implements ORAData, ORADataFactory {
    public static final String _SQL_BASETYPE = "MDSYS.SDO_GEOMETRY";
    public static final int _SQL_TYPECODE = OracleTypes.REF;

    REF _ref;

    private static final SdoGeometryRef _SdoGeometryRefFactory = new SdoGeometryRef();

    public static ORADataFactory getORADataFactory() {
        return _SdoGeometryRefFactory;
    }

    /* constructor */
    public SdoGeometryRef() {
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
        SdoGeometryRef r = new SdoGeometryRef();
        r._ref = (REF) d;
        return r;
    }

    public static SdoGeometryRef cast(ORAData o) throws SQLException {
        if (o == null)
            return null;
        try {
            return (SdoGeometryRef) getORADataFactory().create(o.toDatum(null),
                    OracleTypes.REF);
        } catch (Exception exn) {
            throw new SQLException("Unable to convert "
                    + o.getClass().getName() + " to SdoGeometryRef: "
                    + exn.toString());
        }
    }

    public SdoGeometry getValue() throws SQLException {
        return (SdoGeometry) SdoGeometry.getORADataFactory().create(
                _ref.getSTRUCT(), OracleTypes.REF);
    }

    public void setValue(SdoGeometry c) throws SQLException {
        _ref.setValue(c.toDatum(_ref.getJavaSqlConnection()));
    }
}
