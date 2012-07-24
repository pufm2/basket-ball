package puf.m2.basket.db.entity;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.STRUCT;
import oracle.jpub.runtime.MutableStruct;
import puf.m2.basket.model.entity.User;

public class DbUser implements ORAData, ORADataFactory {
    public static final String _SQL_NAME = "BASKET_USER.T_USER";
    public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

    protected MutableStruct _struct;

    protected static int[] _sqlType = { 4, 12, 12, 4 };
    protected static ORADataFactory[] _factory = new ORADataFactory[4];
    protected static final DbUser _DbUserFactory = new DbUser();

    public static ORADataFactory getORADataFactory() {
        return _DbUserFactory;
    }

    /* constructors */
    protected void _init_struct(boolean init) {
        if (init)
            _struct = new MutableStruct(new Object[4], _sqlType, _factory);
    }

    public DbUser() {
        _init_struct(true);
    }

    public DbUser(Integer id, String username, String password, Integer deleted)
            throws SQLException {
        _init_struct(true);
        setId(id);
        setUsername(username);
        setPassword(password);
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

    protected ORAData create(DbUser o, Datum d, int sqlType)
            throws SQLException {
        if (d == null)
            return null;
        if (o == null)
            o = new User();
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

    public String getUsername() throws SQLException {
        return (String) _struct.getAttribute(1);
    }

    public void setUsername(String username) throws SQLException {
        _struct.setAttribute(1, username);
    }

    public String getPassword() throws SQLException {
        return (String) _struct.getAttribute(2);
    }

    public void setPassword(String password) throws SQLException {
        _struct.setAttribute(2, password);
    }

    public Integer getDeleted() throws SQLException {
        return (Integer) _struct.getAttribute(3);
    }

    public void setDeleted(Integer deleted) throws SQLException {
        _struct.setAttribute(3, deleted);
    }

}
