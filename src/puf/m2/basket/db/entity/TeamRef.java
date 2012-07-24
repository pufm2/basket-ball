package puf.m2.basket.db.entity;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.REF;
import puf.m2.basket.model.entity.Team;

public class TeamRef implements ORAData, ORADataFactory {
    public static final String _SQL_BASETYPE = "BASKET_USER.T_TEAM";
    public static final int _SQL_TYPECODE = OracleTypes.REF;

    REF _ref;

    private static final TeamRef _TeamRefFactory = new TeamRef();

    public static ORADataFactory getORADataFactory() {
        return _TeamRefFactory;
    }

    /* constructor */
    public TeamRef() {
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
        TeamRef r = new TeamRef();
        r._ref = (REF) d;
        return r;
    }

    public static TeamRef cast(ORAData o) throws SQLException {
        if (o == null)
            return null;
        try {
            return (TeamRef) getORADataFactory().create(o.toDatum(null),
                    OracleTypes.REF);
        } catch (Exception exn) {
            throw new SQLException("Unable to convert "
                    + o.getClass().getName() + " to TeamRef: " + exn.toString());
        }
    }

    public Team getValue() throws SQLException {
        return (Team) Team.getORADataFactory().create(_ref.getSTRUCT(),
                OracleTypes.REF);
    }

    public void setValue(Team c) throws SQLException {
        _ref.setValue(c.toDatum(_ref.getJavaSqlConnection()));
    }
}
