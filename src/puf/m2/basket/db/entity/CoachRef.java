package puf.m2.basket.db.entity;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.REF;
import puf.m2.basket.model.entity.Coach;

public class CoachRef implements ORAData, ORADataFactory {
    public static final String _SQL_BASETYPE = "BASKET_USER.T_COACH";
    public static final int _SQL_TYPECODE = OracleTypes.REF;

    REF _ref;

    private static final CoachRef _CoachRefFactory = new CoachRef();

    public static ORADataFactory getORADataFactory() {
        return _CoachRefFactory;
    }

    /* constructor */
    public CoachRef() {
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
        CoachRef r = new CoachRef();
        r._ref = (REF) d;
        return r;
    }

    public static CoachRef cast(ORAData o) throws SQLException {
        if (o == null)
            return null;
        try {
            return (CoachRef) getORADataFactory().create(o.toDatum(null),
                    OracleTypes.REF);
        } catch (Exception exn) {
            throw new SQLException("Unable to convert "
                    + o.getClass().getName() + " to CoachRef: "
                    + exn.toString());
        }
    }

    public Coach getValue() throws SQLException {
        return (Coach) Coach.getORADataFactory().create(_ref.getSTRUCT(),
                OracleTypes.REF);
    }

    public void setValue(Coach c) throws SQLException {
        _ref.setValue(c.toDatum(_ref.getJavaSqlConnection()));
    }
}
