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

public class DbCoach extends Person implements ORAData, ORADataFactory {
    public static final String _SQL_NAME = "BASKET_USER.T_COACH";
    public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

    protected static int[] _sqlType = { 4, 12, 2003 };
    protected static ORADataFactory[] _factory = new ORADataFactory[3];
    static {
        _factory[2] = DbTeamWithCoach.getORADataFactory();
    }
    protected static final DbCoach _DbCoachFactory = new DbCoach();

    public static ORADataFactory getORADataFactory() {
        return _DbCoachFactory;
    }

    static {
        _map.put("BASKET_USER.T_COACH", _DbCoachFactory);
    }

    /* constructors */
    @Override
    protected void _init_struct(boolean init) {
        if (init)
            _struct = new MutableStruct(new Object[3], _sqlType, _factory);
    }

    public DbCoach() {
        _init_struct(true);
    }

    public DbCoach(Integer id, String personName, DbTeamWithCoach teamWithCoach)
            throws SQLException {
        _init_struct(true);
        setId(id);
        setPersonName(personName);
        setTeamWithCoach(teamWithCoach);
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

    protected ORAData create(DbCoach o, Datum d, int sqlType)
            throws SQLException {
        if (d == null)
            return null;
        if (o == null)
            return createFromFactory("DbCoach", d, sqlType);
        o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
        return o;
    }

    @Override
    protected ORAData createExact(Datum d, int sqlType) throws SQLException {
        DbCoach o = new DbCoach();
        o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
        return o;
    }

    /* accessor methods */
    public DbTeamWithCoach getTeamWithCoach() throws SQLException {
        return (DbTeamWithCoach) _struct.getAttribute(2);
    }

    public void setTeamWithCoach(DbTeamWithCoach teamWithCoach)
            throws SQLException {
        _struct.setAttribute(2, teamWithCoach);
    }

}
