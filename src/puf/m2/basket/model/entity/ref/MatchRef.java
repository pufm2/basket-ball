package puf.m2.basket.model.entity.ref;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.REF;
import oracle.sql.STRUCT;
import puf.m2.basket.model.entity.Match;

public class MatchRef implements ORAData, ORADataFactory {
    public static final String _SQL_BASETYPE = "BASKET_USER.T_MATCH";
    public static final int _SQL_TYPECODE = OracleTypes.REF;

    REF _ref;

    private static final MatchRef _MatchRefFactory = new MatchRef();

    public static ORADataFactory getORADataFactory() {
        return _MatchRefFactory;
    }

    /* constructor */
    public MatchRef() {
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
        MatchRef r = new MatchRef();
        r._ref = (REF) d;
        return r;
    }

    public static MatchRef cast(ORAData o) throws SQLException {
        if (o == null)
            return null;
        try {
            return (MatchRef) getORADataFactory().create(o.toDatum(null),
                    OracleTypes.REF);
        } catch (Exception exn) {
            throw new SQLException("Unable to convert "
                    + o.getClass().getName() + " to MatchRef: "
                    + exn.toString());
        }
    }

    public Match getValue() throws SQLException {
        return (Match) Match.getORADataFactory().create(_ref.getSTRUCT(),
                OracleTypes.REF);
    }

    public void setValue(Match c) throws SQLException {
        _ref.setValue(c.toDatum(_ref.getJavaSqlConnection()));
    }
}
