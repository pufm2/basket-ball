package puf.m2.basket.db.entity;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.STRUCT;
import oracle.jpub.runtime.MutableStruct;

public class SdoPointType implements ORAData, ORADataFactory {
    public static final String _SQL_NAME = "MDSYS.SDO_POINT_TYPE";
    public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

    protected MutableStruct _struct;

    protected static int[] _sqlType = { 2, 2, 2 };
    protected static ORADataFactory[] _factory = new ORADataFactory[3];
    protected static final SdoPointType _SdoPointTypeFactory = new SdoPointType();

    public static ORADataFactory getORADataFactory() {
        return _SdoPointTypeFactory;
    }

    /* constructors */
    protected void _init_struct(boolean init) {
        if (init)
            _struct = new MutableStruct(new Object[3], _sqlType, _factory);
    }

    public SdoPointType() {
        _init_struct(true);
    }

    public SdoPointType(java.math.BigDecimal x, java.math.BigDecimal y,
            java.math.BigDecimal z) throws SQLException {
        _init_struct(true);
        setX(x);
        setY(y);
        setZ(z);
    }

    /* ORAData interface */
    @Override
    public Datum toDatum(Connection c) throws SQLException {
        return _struct.toDatum(c, _SQL_NAME);
    }

    /* ORADataFactory interface */
    @Override
    public ORAData create(Datum d, int sqlType) throws SQLException {
        return create(null, d, sqlType);
    }

    protected ORAData create(SdoPointType o, Datum d, int sqlType)
            throws SQLException {
        if (d == null)
            return null;
        if (o == null)
            o = new SdoPointType();
        o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
        return o;
    }

    /* accessor methods */
    public java.math.BigDecimal getX() throws SQLException {
        return (java.math.BigDecimal) _struct.getAttribute(0);
    }

    public void setX(java.math.BigDecimal x) throws SQLException {
        _struct.setAttribute(0, x);
    }

    public java.math.BigDecimal getY() throws SQLException {
        return (java.math.BigDecimal) _struct.getAttribute(1);
    }

    public void setY(java.math.BigDecimal y) throws SQLException {
        _struct.setAttribute(1, y);
    }

    public java.math.BigDecimal getZ() throws SQLException {
        return (java.math.BigDecimal) _struct.getAttribute(2);
    }

    public void setZ(java.math.BigDecimal z) throws SQLException {
        _struct.setAttribute(2, z);
    }

}
