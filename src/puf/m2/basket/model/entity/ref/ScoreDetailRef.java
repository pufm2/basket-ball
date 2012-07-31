package puf.m2.basket.model.entity.ref;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.REF;
import oracle.sql.STRUCT;
import puf.m2.basket.model.entity.ScoreDetail;

public class ScoreDetailRef implements ORAData, ORADataFactory {
    public static final String _SQL_BASETYPE = "BASKET_USER.T_SCOREDETAIL";
    public static final int _SQL_TYPECODE = OracleTypes.REF;

    REF _ref;

    private static final ScoreDetailRef _ScoreDetailRefFactory = new ScoreDetailRef();

    public static ORADataFactory getORADataFactory() {
        return _ScoreDetailRefFactory;
    }

    /* constructor */
    public ScoreDetailRef() {
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
        ScoreDetailRef r = new ScoreDetailRef();
        r._ref = (REF) d;
        return r;
    }

    public static ScoreDetailRef cast(ORAData o) throws SQLException {
        if (o == null)
            return null;
        try {
            return (ScoreDetailRef) getORADataFactory().create(o.toDatum(null),
                    OracleTypes.REF);
        } catch (Exception exn) {
            throw new SQLException("Unable to convert "
                    + o.getClass().getName() + " to ScoreDetailRef: "
                    + exn.toString());
        }
    }

    public ScoreDetail getValue() throws SQLException {
        return (ScoreDetail) ScoreDetail.getORADataFactory().create(
                _ref.getSTRUCT(), OracleTypes.REF);
    }

    public void setValue(ScoreDetail c) throws SQLException {
        _ref.setValue(c.toDatum(_ref.getJavaSqlConnection()));
    }
}
