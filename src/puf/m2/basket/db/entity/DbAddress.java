package puf.m2.basket.db.entity;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.STRUCT;
import oracle.jpub.runtime.MutableStruct;
import puf.m2.basket.model.entity.Address;

public class DbAddress implements ORAData, ORADataFactory {
    public static final String _SQL_NAME = "BASKET_USER.T_ADDRESS";
    public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

    protected MutableStruct _struct;

    protected static int[] _sqlType = { 12, 12, 12, 12 };
    protected static ORADataFactory[] _factory = new ORADataFactory[4];
    protected static final DbAddress _DbAddressFactory = new DbAddress();

    public static ORADataFactory getORADataFactory() {
        return _DbAddressFactory;
    }

    /* constructors */
    protected void _init_struct(boolean init) {
        if (init)
            _struct = new MutableStruct(new Object[4], _sqlType, _factory);
    }

    public DbAddress() {
        _init_struct(true);
    }

    public DbAddress(String addressNumber, String addressStreet,
            String addressDistrict, String addressCity) throws SQLException {
        _init_struct(true);
        setAddressNumber(addressNumber);
        setAddressStreet(addressStreet);
        setAddressDistrict(addressDistrict);
        setAddressCity(addressCity);
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

    protected ORAData create(DbAddress o, Datum d, int sqlType)
            throws SQLException {
        if (d == null)
            return null;
        if (o == null)
            o = new Address();
        o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
        return o;
    }

    /* accessor methods */
    public String getAddressNumber() throws SQLException {
        return (String) _struct.getAttribute(0);
    }

    public void setAddressNumber(String addressNumber) throws SQLException {
        _struct.setAttribute(0, addressNumber);
    }

    public String getAddressStreet() throws SQLException {
        return (String) _struct.getAttribute(1);
    }

    public void setAddressStreet(String addressStreet) throws SQLException {
        _struct.setAttribute(1, addressStreet);
    }

    public String getAddressDistrict() throws SQLException {
        return (String) _struct.getAttribute(2);
    }

    public void setAddressDistrict(String addressDistrict) throws SQLException {
        _struct.setAttribute(2, addressDistrict);
    }

    public String getAddressCity() throws SQLException {
        return (String) _struct.getAttribute(3);
    }

    public void setAddressCity(String addressCity) throws SQLException {
        _struct.setAttribute(3, addressCity);
    }

}
