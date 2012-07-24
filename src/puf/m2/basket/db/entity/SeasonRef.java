package puf.m2.basket.db.entity;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.REF;
import puf.m2.basket.model.entity.Season;

public class SeasonRef implements ORAData, ORADataFactory {
    public static final String _SQL_BASETYPE = "BASKET_USER.T_SEASON";
    public static final int _SQL_TYPECODE = OracleTypes.REF;

    REF _ref;

    private static final SeasonRef _SeasonRefFactory = new SeasonRef();

    public static ORADataFactory getORADataFactory() {
        return _SeasonRefFactory;
    }

    /* constructor */
    public SeasonRef() {
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
        SeasonRef r = new SeasonRef();
        r._ref = (REF) d;
        return r;
    }

    public static SeasonRef cast(ORAData o) throws SQLException {
        if (o == null)
            return null;
        try {
            return (SeasonRef) getORADataFactory().create(o.toDatum(null),
                    OracleTypes.REF);
        } catch (Exception exn) {
            throw new SQLException("Unable to convert "
                    + o.getClass().getName() + " to SeasonRef: "
                    + exn.toString());
        }
    }

    public Season getValue() throws SQLException {
        return (Season) Season.getORADataFactory().create(_ref.getSTRUCT(),
                OracleTypes.REF);
    }

    public void setValue(Season c) throws SQLException {
        _ref.setValue(c.toDatum(_ref.getJavaSqlConnection()));
    }
}
