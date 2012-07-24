package puf.m2.basket.db.entity;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.STRUCT;
import oracle.jpub.runtime.MutableStruct;

public class DbTeamMany implements ORAData, ORADataFactory {
    public static final String _SQL_NAME = "BASKET_USER.T_TEAM_MANY";
    public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

    protected MutableStruct _struct;

    protected static int[] _sqlType = { 2006, 91, 91 };
    protected static ORADataFactory[] _factory = new ORADataFactory[3];
    static {
        _factory[0] = TeamRef.getORADataFactory();
    }
    protected static final DbTeamMany _DbTeamManyFactory = new DbTeamMany();

    public static ORADataFactory getORADataFactory() {
        return _DbTeamManyFactory;
    }

    protected static java.util.Hashtable _map = new java.util.Hashtable();
    protected static boolean _initialized = false;

    protected static synchronized void init() {
        if (!_initialized) {
            _initialized = true;
            _map.put("BASKET_USER.T_TEAM_MANY",
                    puf.m2.basket.db.entity.DbTeamMany.getORADataFactory());
        }
    }

    /* constructors */
    protected void _init_struct(boolean init) {
        if (init)
            _struct = new MutableStruct(new Object[3], _sqlType, _factory);
    }

    public DbTeamMany() {
        _init_struct(true);
    }

    public DbTeamMany(TeamRef manyTeam, java.sql.Timestamp startDate,
            java.sql.Timestamp endDate) throws SQLException {
        _init_struct(true);
        setManyTeam(manyTeam);
        setStartDate(startDate);
        setEndDate(endDate);
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

    protected ORAData create(DbTeamMany o, Datum d, int sqlType)
            throws SQLException {
        if (d == null)
            return null;
        if (o == null)
            return createFromFactory("DbTeamMany", d, sqlType);
        o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
        return o;
    }

    protected ORAData createExact(Datum d, int sqlType) throws SQLException {
        DbTeamMany o = new DbTeamMany();
        o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
        return o;
    }

    protected ORAData createFromFactory(String s, Datum d, int sqlType)
            throws SQLException {
        String sql = ((STRUCT) d).getSQLTypeName();
        init();
        DbTeamMany factory = (DbTeamMany) _map.get(sql);
        if (factory == null) {
            int p;
            if ((p = sql.indexOf(".")) >= 0) {
                factory = (DbTeamMany) _map.get(sql.substring(p + 1));
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
    public TeamRef getManyTeam() throws SQLException {
        return (TeamRef) _struct.getAttribute(0);
    }

    public void setManyTeam(TeamRef manyTeam) throws SQLException {
        _struct.setAttribute(0, manyTeam);
    }

    public java.sql.Timestamp getStartDate() throws SQLException {
        return (java.sql.Timestamp) _struct.getAttribute(1);
    }

    public void setStartDate(java.sql.Timestamp startDate) throws SQLException {
        _struct.setAttribute(1, startDate);
    }

    public java.sql.Timestamp getEndDate() throws SQLException {
        return (java.sql.Timestamp) _struct.getAttribute(2);
    }

    public void setEndDate(java.sql.Timestamp endDate) throws SQLException {
        _struct.setAttribute(2, endDate);
    }

}
