package puf.m2.basket.db.entity;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.REF;
import puf.m2.basket.model.entity.Player;

public class PlayerRef implements ORAData, ORADataFactory {
    public static final String _SQL_BASETYPE = "BASKET_USER.T_PLAYER";
    public static final int _SQL_TYPECODE = OracleTypes.REF;

    REF _ref;

    private static final PlayerRef _PlayerRefFactory = new PlayerRef();

    public static ORADataFactory getORADataFactory() {
        return _PlayerRefFactory;
    }

    /* constructor */
    public PlayerRef() {
    }

    /* ORAData interface */
    @Override
    public Datum toDatum(Connection c) throws SQLException {
        return _ref;
    }

    /* ORADataFactory interface */
    @Override
    public ORAData create(Datum d, int sqlType) throws SQLException {
        if (d == null)
            return null;
        PlayerRef r = new PlayerRef();
        r._ref = (REF) d;
        return r;
    }

    public static PlayerRef cast(ORAData o) throws SQLException {
        if (o == null)
            return null;
        try {
            return (PlayerRef) getORADataFactory().create(o.toDatum(null),
                    OracleTypes.REF);
        } catch (Exception exn) {
            throw new SQLException("Unable to convert "
                    + o.getClass().getName() + " to PlayerRef: "
                    + exn.toString());
        }
    }

    public Player getValue() throws SQLException {
        return (Player) Player.getORADataFactory().create(_ref.getSTRUCT(),
                OracleTypes.REF);
    }

    public void setValue(Player c) throws SQLException {
        _ref.setValue(c.toDatum(_ref.getJavaSqlConnection()));
    }
}
