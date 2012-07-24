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
import puf.m2.basket.model.entity.Office;

public class DbOffice extends Entity implements ORAData, ORADataFactory {
    public static final String _SQL_NAME = "BASKET_USER.T_OFFICE";
    public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

    protected MutableStruct _struct;

    protected static int[] _sqlType = { 4, 12, 2002 };
    protected static ORADataFactory[] _factory = new ORADataFactory[3];
    static {
        _factory[2] = Address.getORADataFactory();
    }
    protected static final DbOffice _DbOfficeFactory = new DbOffice();

    public static ORADataFactory getORADataFactory() {
        return _DbOfficeFactory;
    }

    /* constructors */
    protected void _init_struct(boolean init) {
        if (init)
            _struct = new MutableStruct(new Object[3], _sqlType, _factory);
    }

    public DbOffice() {
        _init_struct(true);
    }

    public DbOffice(Integer id, String officeName, Address officeAddress)
            throws SQLException {
        _init_struct(true);
        setId(id);
        setOfficeName(officeName);
        setOfficeAddress(officeAddress);
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

    protected ORAData create(DbOffice o, Datum d, int sqlType)
            throws SQLException {
        if (d == null)
            return null;
        if (o == null)
            o = new Office();
        o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
        return o;
    }

    /* accessor methods */
    public Integer getId() throws SQLException {
        return (Integer) _struct.getAttribute(0);
    }

    public void setId(Integer id) throws SQLException {
        _struct.setAttribute(0, id);
    }

    public String getOfficeName() throws SQLException {
        return (String) _struct.getAttribute(1);
    }

    public void setOfficeName(String officeName) throws SQLException {
        _struct.setAttribute(1, officeName);
    }

    public Address getOfficeAddress() throws SQLException {
        return (Address) _struct.getAttribute(2);
    }

    public void setOfficeAddress(Address officeAddress) throws SQLException {
        _struct.setAttribute(2, officeAddress);
    }

}
