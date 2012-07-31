package puf.m2.basket.db.entity;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.STRUCT;
import oracle.jpub.runtime.MutableStruct;
import puf.m2.basket.model.entity.ScoreDetail;
import puf.m2.basket.model.entity.ref.PlayerRef;
import puf.m2.basket.model.entity.ref.TeamRef;

public class DbScoreDetail implements ORAData, ORADataFactory {
    public static final String _SQL_NAME = "BASKET_USER.T_SCOREDETAIL";
    public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

    protected MutableStruct _struct;

    protected static int[] _sqlType = { 4, 2006, 2006, 4 };
    protected static ORADataFactory[] _factory = new ORADataFactory[4];
    static {
        _factory[1] = TeamRef.getORADataFactory();
        _factory[2] = PlayerRef.getORADataFactory();
    }
    protected static final DbScoreDetail _DbScoreDetailFactory = new DbScoreDetail();

    public static ORADataFactory getORADataFactory() {
        return _DbScoreDetailFactory;
    }

    /* constructors */
    protected void _init_struct(boolean init) {
        if (init)
            _struct = new MutableStruct(new Object[4], _sqlType, _factory);
    }

    public DbScoreDetail() {
        _init_struct(true);
    }

    public DbScoreDetail(Integer id, TeamRef team, PlayerRef player,
            Integer value) throws SQLException {
        _init_struct(true);
        setId(id);
        setTeam(team);
        setPlayer(player);
        setValue(value);
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

    protected ORAData create(DbScoreDetail o, Datum d, int sqlType)
            throws SQLException {
        if (d == null)
            return null;
        if (o == null)
            o = new ScoreDetail();
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

    public TeamRef getTeam() throws SQLException {
        return (TeamRef) _struct.getAttribute(1);
    }

    public void setTeam(TeamRef team) throws SQLException {
        _struct.setAttribute(1, team);
    }

    public PlayerRef getPlayer() throws SQLException {
        return (PlayerRef) _struct.getAttribute(2);
    }

    public void setPlayer(PlayerRef player) throws SQLException {
        _struct.setAttribute(2, player);
    }

    public Integer getValue() throws SQLException {
        return (Integer) _struct.getAttribute(3);
    }

    public void setValue(Integer value) throws SQLException {
        _struct.setAttribute(3, value);
    }

}
