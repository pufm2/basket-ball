package puf.m2.basket.db.entity;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.STRUCT;
import oracle.jpub.runtime.MutableStruct;
import puf.m2.basket.model.entity.Match;
import puf.m2.basket.model.support.DbProp;

public class DbMatch extends Entity implements ORAData, ORADataFactory {
    public static final String _SQL_NAME = "BASKET_USER.T_MATCH";
    public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

    protected MutableStruct _struct;

    protected static int[] _sqlType = { 4, 91, 2006, 2003 };
    protected static ORADataFactory[] _factory = new ORADataFactory[4];
    static {
        _factory[2] = SeasonRef.getORADataFactory();
        _factory[3] = DbScoreDetails.getORADataFactory();
    }
    protected static final DbMatch _DbMatchFactory = new DbMatch();

    public static ORADataFactory getORADataFactory() {
        return _DbMatchFactory;
    }

    /* constructors */
    protected void _init_struct(boolean init) {
        if (init)
            _struct = new MutableStruct(new Object[4], _sqlType, _factory);
    }

    public DbMatch() {
        _init_struct(true);
    }

    public DbMatch(Integer id, java.sql.Timestamp matchDate, SeasonRef season,
            DbScoreDetails details) throws SQLException {
        _init_struct(true);
        setId(id);
        setMatchDate(matchDate);
        setSeason(season);
        setDetails(details);
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
    public SeasonRef getSeason() throws SQLException {
        return (SeasonRef) _struct.getAttribute(2);
    }

    public void setSeason(SeasonRef season) throws SQLException {
        _struct.setAttribute(2, season);
    }

    @DbProp
    public DbScoreDetails getDetails() throws SQLException {
        return (DbScoreDetails) _struct.getAttribute(3);
    }

    public void setDetails(DbScoreDetails details) throws SQLException {
        _struct.setAttribute(3, details);
    }

}
