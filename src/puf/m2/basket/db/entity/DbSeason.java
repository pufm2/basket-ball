package puf.m2.basket.db.entity;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.STRUCT;
import oracle.jpub.runtime.MutableStruct;
import puf.m2.basket.model.entity.Season;
import puf.m2.basket.model.support.DbProp;

public class DbSeason extends Entity implements ORAData, ORADataFactory {
    public static final String _SQL_NAME = "BASKET_USER.T_SEASON";
    public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

    protected MutableStruct _struct;

    protected static int[] _sqlType = { 4, 12, 91, 91, 4 };
    protected static ORADataFactory[] _factory = new ORADataFactory[5];
    protected static final DbSeason _DbSeasonFactory = new DbSeason();

    public static ORADataFactory getORADataFactory() {
        return _DbSeasonFactory;
    }

    /* constructors */
    protected void _init_struct(boolean init) {
        if (init)
            _struct = new MutableStruct(new Object[5], _sqlType, _factory);
    }

    public DbSeason() {
        _init_struct(true);
    }

    public DbSeason(Integer id, String seasonName,
            java.sql.Timestamp seasonStartdate,
            java.sql.Timestamp seasonEnddate, Integer deleted)
            throws SQLException {
        _init_struct(true);
        setId(id);
        setSeasonName(seasonName);
        setSeasonStartdate(seasonStartdate);
        setSeasonEnddate(seasonEnddate);
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

    protected ORAData create(DbSeason o, Datum d, int sqlType)
            throws SQLException {
        if (d == null)
            return null;
        if (o == null)
            o = new Season();
        o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
        return o;
    }

    /* accessor methods */
    @Override
    @DbProp
    public Integer getId() throws SQLException {
        return (Integer) _struct.getAttribute(0);
    }

    public void setId(Integer id) throws SQLException {
        _struct.setAttribute(0, id);
    }

    @DbProp
    public String getSeasonName() throws SQLException {
        return (String) _struct.getAttribute(1);
    }

    public void setSeasonName(String seasonName) throws SQLException {
        _struct.setAttribute(1, seasonName);
    }

    @DbProp
    public java.sql.Timestamp getSeasonStartdate() throws SQLException {
        return (java.sql.Timestamp) _struct.getAttribute(2);
    }

    public void setSeasonStartdate(java.sql.Timestamp seasonStartdate)
            throws SQLException {
        _struct.setAttribute(2, seasonStartdate);
    }

    @DbProp
    public java.sql.Timestamp getSeasonEnddate() throws SQLException {
        return (java.sql.Timestamp) _struct.getAttribute(3);
    }

    public void setSeasonEnddate(java.sql.Timestamp seasonEnddate)
            throws SQLException {
        _struct.setAttribute(3, seasonEnddate);
    }

    @DbProp
    public Integer getDeleted() throws SQLException {
        return (Integer) _struct.getAttribute(4);
    }

    public void setDeleted(Integer deleted) throws SQLException {
        _struct.setAttribute(4, deleted);
    }

}
