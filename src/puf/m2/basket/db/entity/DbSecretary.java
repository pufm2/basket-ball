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
import puf.m2.basket.model.entity.Secretary;

public class DbSecretary extends Person implements ORAData, ORADataFactory {
    public static final String _SQL_NAME = "BASKET_USER.T_SECRETARY";
    public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

    protected static int[] _sqlType = { 4, 12, 4 };
    protected static ORADataFactory[] _factory = new ORADataFactory[3];
    protected static final DbSecretary _DbSecretaryFactory = new DbSecretary();

    public static ORADataFactory getORADataFactory() {
        return _DbSecretaryFactory;
    }

    static {
        _map.put("BASKET_USER.T_SECRETARY", _DbSecretaryFactory);
    }

    /* constructors */
    @Override
    protected void _init_struct(boolean init) {
        if (init)
            _struct = new MutableStruct(new Object[3], _sqlType, _factory);
    }

    public DbSecretary() {
        _init_struct(true);
    }

    public DbSecretary(Integer id, String personName, Integer deleted)
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

    protected ORAData create(DbSecretary o, Datum d, int sqlType)
            throws SQLException {
        if (d == null)
            return null;
        if (o == null)
            o = new Secretary();
        o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
        return o;
    }

    @Override
    protected ORAData createExact(Datum d, int sqlType) throws SQLException {
        return create(null, d, sqlType);
    }

    /* accessor methods */
}
