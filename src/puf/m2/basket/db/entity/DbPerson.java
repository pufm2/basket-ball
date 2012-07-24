package puf.m2.basket.db.entity;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.STRUCT;
import oracle.jpub.runtime.MutableStruct;
import puf.m2.basket.model.support.DbProp;

public class DbPerson extends Entity implements ORAData, ORADataFactory {
    public static final String _SQL_NAME = "BASKET_USER.T_PERSON";
    public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

    protected MutableStruct _struct;

    protected static int[] _sqlType = { 4, 12 };
    protected static ORADataFactory[] _factory = new ORADataFactory[2];
    protected static final DbPerson _DbPersonFactory = new DbPerson();

    public static ORADataFactory getORADataFactory() {
        return _DbPersonFactory;
    }

    protected static java.util.Hashtable _map = new java.util.Hashtable();
    protected static boolean _initialized = false;

    protected static synchronized void init() {
        if (!_initialized) {
            _initialized = true;
            _map.put("BASKET_USER.T_PERSON",
                    puf.m2.basket.model.entity.Person.getORADataFactory());
            _map.put("BASKET_USER.T_COACH",
                    puf.m2.basket.model.entity.Coach.getORADataFactory());
            _map.put("BASKET_USER.T_PLAYER",
                    puf.m2.basket.model.entity.Player.getORADataFactory());
            _map.put("BASKET_USER.T_PRESIDENT",
                    puf.m2.basket.model.entity.President.getORADataFactory());
            _map.put("BASKET_USER.T_SECRETARY",
                    puf.m2.basket.model.entity.Secretary.getORADataFactory());
            _map.put("BASKET_USER.T_TREASURER",
                    puf.m2.basket.model.entity.Treasurer.getORADataFactory());
            _map.put("BASKET_USER.T_VICE_PRESIDENT",
                    puf.m2.basket.model.entity.VicePresident.getORADataFactory());
        }
    }

    /* constructors */
    protected void _init_struct(boolean init) {
        if (init)
            _struct = new MutableStruct(new Object[2], _sqlType, _factory);
    }

    protected DbPerson() {
        _init_struct(true);
    }

    protected DbPerson(Integer id, String personName) throws SQLException {
        _init_struct(true);
        setId(id);
        setPersonName(personName);
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

    protected ORAData create(DbPerson o, Datum d, int sqlType)
            throws SQLException {
        if (d == null)
            return null;
        if (o == null)
            return createFromFactory("DbPerson", d, sqlType);
        o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
        return o;
    }

    protected ORAData createExact(Datum d, int sqlType) throws SQLException {
        DbPerson o = new DbPerson();
        o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
        return o;
    }

    protected ORAData createFromFactory(String s, Datum d, int sqlType)
            throws SQLException {
        String sql = ((STRUCT) d).getSQLTypeName();
        init();
        DbPerson factory = (DbPerson) _map.get(sql);
        if (factory == null) {
            int p;
            if ((p = sql.indexOf(".")) >= 0) {
                factory = (DbPerson) _map.get(sql.substring(p + 1));
                if (factory != null)
                    _map.put(sql, factory);
            }
            if (factory == null)
                throw new SQLException("Unable to convert a " + sql + " to a "
                        + s + " or a subclass of " + s);
        }
        return factory.createExact(d, sqlType);
    }

    /* accessor methods */
    @DbProp
    public Integer getId() throws SQLException {
        return (Integer) _struct.getAttribute(0);
    }

    public void setId(Integer id) throws SQLException {
        _struct.setAttribute(0, id);
    }

    @DbProp
    public String getPersonName() throws SQLException {
        return (String) _struct.getAttribute(1);
    }

    public void setPersonName(String personName) throws SQLException {
        _struct.setAttribute(1, personName);
    }

}
