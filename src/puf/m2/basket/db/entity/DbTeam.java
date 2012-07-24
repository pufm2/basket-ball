package puf.m2.basket.db.entity;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.STRUCT;
import oracle.jpub.runtime.MutableStruct;
import puf.m2.basket.model.entity.Team;
import puf.m2.basket.model.support.DbProp;

public class DbTeam extends Entity implements ORAData, ORADataFactory {
    public static final String _SQL_NAME = "BASKET_USER.T_TEAM";
    public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

    protected MutableStruct _struct;

    protected static int[] _sqlType = { 4, 12 };
    protected static ORADataFactory[] _factory = new ORADataFactory[2];
    protected static final DbTeam _DbTeamFactory = new DbTeam();

    public static ORADataFactory getORADataFactory() {
        return _DbTeamFactory;
    }

    /* constructors */
    protected void _init_struct(boolean init) {
        if (init)
            _struct = new MutableStruct(new Object[2], _sqlType, _factory);
    }

    public DbTeam() {
        _init_struct(true);
    }

    public DbTeam(Integer id, String teamName) throws SQLException {
        _init_struct(true);
        setId(id);
        setTeamName(teamName);
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

    protected ORAData create(DbTeam o, Datum d, int sqlType)
            throws SQLException {
        if (d == null)
            return null;
        if (o == null)
            o = new Team();
        o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
        return o;
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
    public String getTeamName() throws SQLException {
        return (String) _struct.getAttribute(1);
    }

    public void setTeamName(String teamName) throws SQLException {
        _struct.setAttribute(1, teamName);
    }

}
