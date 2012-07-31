package puf.m2.basket.db.entity;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.STRUCT;
import oracle.jpub.runtime.MutableStruct;
import puf.m2.basket.model.entity.Address;
import puf.m2.basket.model.entity.Person;
import puf.m2.basket.model.support.DbProp;

public class DbPlayer extends Person implements ORAData, ORADataFactory {
    public static final String _SQL_NAME = "BASKET_USER.T_PLAYER";
    public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

    protected static int[] _sqlType = { 4, 12, 4, 12, 91, 2002, 2003 };
    protected static ORADataFactory[] _factory = new ORADataFactory[7];
    static {
        _factory[5] = Address.getORADataFactory();
        _factory[6] = DbTeamWithPlayer.getORADataFactory();
    }
    protected static final DbPlayer _DbPlayerFactory = new DbPlayer();

    public static ORADataFactory getORADataFactory() {
        return _DbPlayerFactory;
    }

    static {
        _map.put("BASKET_USER.T_PLAYER", _DbPlayerFactory);
    }

    /* constructors */
    @Override
    protected void _init_struct(boolean init) {
        if (init)
            _struct = new MutableStruct(new Object[7], _sqlType, _factory);
    }

    public DbPlayer() {
        _init_struct(true);
    }

    public DbPlayer(Integer id, String personName, Integer deleted,
            String playerLicenceNumber, java.sql.Timestamp playerBirthday,
            Address playerAddress, DbTeamWithPlayer teamWithPlayers)
            throws SQLException {
        _init_struct(true);
        setId(id);
        setPersonName(personName);
        setDeleted(deleted);
        setPlayerLicenceNumber(playerLicenceNumber);
        setPlayerBirthday(playerBirthday);
        setPlayerAddress(playerAddress);
        setTeamWithPlayers(teamWithPlayers);
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

    protected ORAData create(DbPlayer o, Datum d, int sqlType)
            throws SQLException {
        if (d == null)
            return null;
        if (o == null)
            return createFromFactory("DbPlayer", d, sqlType);
        o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
        return o;
    }

    @Override
    protected ORAData createExact(Datum d, int sqlType) throws SQLException {
        DbPlayer o = new DbPlayer();
        o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
        return o;
    }

    /* accessor methods */
    @DbProp
    public String getPlayerLicenceNumber() throws SQLException {
        return (String) _struct.getAttribute(3);
    }

    public void setPlayerLicenceNumber(String playerLicenceNumber)
            throws SQLException {
        _struct.setAttribute(3, playerLicenceNumber);
    }

    @DbProp
    public java.sql.Timestamp getPlayerBirthday() throws SQLException {
        return (java.sql.Timestamp) _struct.getAttribute(4);
    }

    public void setPlayerBirthday(java.sql.Timestamp playerBirthday)
            throws SQLException {
        _struct.setAttribute(4, playerBirthday);
    }

    @DbProp
    public Address getPlayerAddress() throws SQLException {
        return (Address) _struct.getAttribute(5);
    }

    public void setPlayerAddress(Address playerAddress) throws SQLException {
        _struct.setAttribute(5, playerAddress);
    }

    @DbProp
    public DbTeamWithPlayer getTeamWithPlayers() throws SQLException {
        return (DbTeamWithPlayer) _struct.getAttribute(6);
    }

    public void setTeamWithPlayers(DbTeamWithPlayer teamWithPlayers)
            throws SQLException {
        _struct.setAttribute(6, teamWithPlayers);
    }

}
