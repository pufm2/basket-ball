package puf.m2.basket.db.entity;

import java.sql.Connection;
import java.sql.SQLException;

import oracle.jdbc.OracleTypes;
import oracle.jpub.runtime.MutableStruct;
import oracle.sql.Datum;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.STRUCT;
import puf.m2.basket.model.entity.Match;
import puf.m2.basket.model.entity.ref.SeasonRef;
import puf.m2.basket.model.entity.ref.TeamRef;
import puf.m2.basket.model.support.DbProp;

public class DbMatch extends Entity implements ORAData, ORADataFactory {
    public static final String _SQL_NAME = "BASKET_USER.T_MATCH";
    public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

    protected MutableStruct _struct;

    protected static int[] _sqlType = { 4, 91, 2006, 2006, 2006, 2003, 4 };
    protected static ORADataFactory[] _factory = new ORADataFactory[7];
    static {
        _factory[2] = TeamRef.getORADataFactory();
        _factory[3] = TeamRef.getORADataFactory();
        _factory[4] = SeasonRef.getORADataFactory();
        _factory[5] = DbScoreDetails.getORADataFactory();
    }
    protected static final DbMatch _DbMatchFactory = new DbMatch();

    public static ORADataFactory getORADataFactory() {
        return _DbMatchFactory;
    }

    /* constructors */
    protected void _init_struct(boolean init) {
        if (init)
            _struct = new MutableStruct(new Object[7], _sqlType, _factory);
    }

    public DbMatch() {
        _init_struct(true);
    }

    public DbMatch(Integer id, java.sql.Timestamp matchDate, TeamRef team1,
            TeamRef team2, SeasonRef season, DbScoreDetails details,
            Integer deleted) throws SQLException {
        _init_struct(true);
        setId(id);
        setMatchDate(matchDate);
        setTeam1(team1);
        setTeam2(team2);
        setSeason(season);
        setDetails(details);
        setDeleted(deleted);
    }

    /* ORAData interface */
    public Datum toDatum(Connection c) throws SQLException {
        return _struct.toDatum(c, _SQL_NAME);
    }

    /* ORADataFactory interface */
    public ORAData create(Datum d, int sqlType) throws SQLException {
        return create(null, d, sqlType);
    }

    protected ORAData create(DbMatch o, Datum d, int sqlType)
            throws SQLException {
        if (d == null)
            return null;
        if (o == null)
            o = new Match();
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
    public java.sql.Timestamp getMatchDate() throws SQLException {
        return (java.sql.Timestamp) _struct.getAttribute(1);
    }

    public void setMatchDate(java.sql.Timestamp matchDate) throws SQLException {
        _struct.setAttribute(1, matchDate);
    }

    @DbProp
    public TeamRef getTeam1() throws SQLException {
        return (TeamRef) _struct.getAttribute(2);
    }

    public void setTeam1(TeamRef team1) throws SQLException {
        _struct.setAttribute(2, team1);
    }

    @DbProp
    public TeamRef getTeam2() throws SQLException {
        return (TeamRef) _struct.getAttribute(3);
    }

    public void setTeam2(TeamRef team2) throws SQLException {
        _struct.setAttribute(3, team2);
    }

    @DbProp
    public SeasonRef getSeason() throws SQLException {
        return (SeasonRef) _struct.getAttribute(4);
    }

    public void setSeason(SeasonRef season) throws SQLException {
        _struct.setAttribute(4, season);
    }

    @DbProp
    public DbScoreDetails getDetails() throws SQLException {
        return (DbScoreDetails) _struct.getAttribute(5);
    }

    public void setDetails(DbScoreDetails details) throws SQLException {
        _struct.setAttribute(5, details);
    }

    @DbProp
    public Integer getDeleted() throws SQLException {
        return (Integer) _struct.getAttribute(6);
    }

    public void setDeleted(Integer deleted) throws SQLException {
        _struct.setAttribute(6, deleted);
    }

}
