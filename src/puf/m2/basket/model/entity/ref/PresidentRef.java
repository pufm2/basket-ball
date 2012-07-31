package puf.m2.basket.model.entity.ref;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.REF;
import oracle.sql.STRUCT;
import puf.m2.basket.model.entity.President;

public class PresidentRef implements ORAData, ORADataFactory {
    public static final String _SQL_BASETYPE = "BASKET_USER.T_PRESIDENT";
    public static final int _SQL_TYPECODE = OracleTypes.REF;

    REF _ref;

    private static final PresidentRef _PresidentRefFactory = new PresidentRef();

    public static ORADataFactory getORADataFactory() {
        return _PresidentRefFactory;
    }

    /* constructor */
    public PresidentRef() {
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
        PresidentRef r = new PresidentRef();
        r._ref = (REF) d;
        return r;
    }

    public static PresidentRef cast(ORAData o) throws SQLException {
        if (o == null)
            return null;
        try {
            return (PresidentRef) getORADataFactory().create(o.toDatum(null),
                    OracleTypes.REF);
        } catch (Exception exn) {
            throw new SQLException("Unable to convert "
                    + o.getClass().getName() + " to PresidentRef: "
                    + exn.toString());
        }
    }

    public President getValue() throws SQLException {
        return (President) President.getORADataFactory().create(
                _ref.getSTRUCT(), OracleTypes.REF);
    }

    public void setValue(President c) throws SQLException {
        _ref.setValue(c.toDatum(_ref.getJavaSqlConnection()));
    }
}
