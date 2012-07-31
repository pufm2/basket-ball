package puf.m2.basket.model.entity.ref;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.REF;
import oracle.sql.STRUCT;
import puf.m2.basket.model.entity.Club;

public class ClubRef implements ORAData, ORADataFactory {
    public static final String _SQL_BASETYPE = "BASKET_USER.T_CLUB";
    public static final int _SQL_TYPECODE = OracleTypes.REF;

    REF _ref;

    private static final ClubRef _ClubRefFactory = new ClubRef();

    public static ORADataFactory getORADataFactory() {
        return _ClubRefFactory;
    }

    /* constructor */
    public ClubRef() {
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
        ClubRef r = new ClubRef();
        r._ref = (REF) d;
        return r;
    }

    public static ClubRef cast(ORAData o) throws SQLException {
        if (o == null)
            return null;
        try {
            return (ClubRef) getORADataFactory().create(o.toDatum(null),
                    OracleTypes.REF);
        } catch (Exception exn) {
            throw new SQLException("Unable to convert "
                    + o.getClass().getName() + " to ClubRef: " + exn.toString());
        }
    }

    public Club getValue() throws SQLException {
        return (Club) Club.getORADataFactory().create(_ref.getSTRUCT(),
                OracleTypes.REF);
    }

    public void setValue(Club c) throws SQLException {
        _ref.setValue(c.toDatum(_ref.getJavaSqlConnection()));
    }
}
