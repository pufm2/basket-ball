package puf.m2.basket.db.entity;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.STRUCT;
import oracle.jpub.runtime.MutableStruct;
import puf.m2.basket.model.entity.Person;
import puf.m2.basket.model.entity.VicePresident;

public class DbVicePresident extends Person implements ORAData, ORADataFactory {
    public static final String _SQL_NAME = "BASKET_USER.T_VICE_PRESIDENT";
    public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

    protected static int[] _sqlType = { 4, 12, 4 };
    protected static ORADataFactory[] _factory = new ORADataFactory[3];
    protected static final DbVicePresident _DbVicePresidentFactory = new DbVicePresident();

    public static ORADataFactory getORADataFactory() {
        return _DbVicePresidentFactory;
    }

    static {
        _map.put("BASKET_USER.T_VICE_PRESIDENT", _DbVicePresidentFactory);
    }

    /* constructors */
    @Override
    protected void _init_struct(boolean init) {
        if (init)
            _struct = new MutableStruct(new Object[3], _sqlType, _factory);
    }

    public DbVicePresident() {
        _init_struct(true);
    }

    public DbVicePresident(Integer id, String personName, Integer deleted)
            throws SQLException {
        _init_struct(true);
        setId(id);
        setPersonName(personName);
        setDeleted(deleted);
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

    protected ORAData create(DbVicePresident o, Datum d, int sqlType)
            throws SQLException {
        if (d == null)
            return null;
        if (o == null)
            o = new VicePresident();
        o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
        return o;
    }

    @Override
    protected ORAData createExact(Datum d, int sqlType) throws SQLException {
        return create(null, d, sqlType);
    }

    /* accessor methods */
}
