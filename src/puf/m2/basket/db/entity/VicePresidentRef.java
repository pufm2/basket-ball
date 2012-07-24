package puf.m2.basket.db.entity;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.REF;
import puf.m2.basket.model.entity.VicePresident;

public class VicePresidentRef implements ORAData, ORADataFactory {
    public static final String _SQL_BASETYPE = "BASKET_USER.T_VICE_PRESIDENT";
    public static final int _SQL_TYPECODE = OracleTypes.REF;

    REF _ref;

    private static final VicePresidentRef _VicePresidentRefFactory = new VicePresidentRef();

    public static ORADataFactory getORADataFactory() {
        return _VicePresidentRefFactory;
    }

    /* constructor */
    public VicePresidentRef() {
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
        VicePresidentRef r = new VicePresidentRef();
        r._ref = (REF) d;
        return r;
    }

    public static VicePresidentRef cast(ORAData o) throws SQLException {
        if (o == null)
            return null;
        try {
            return (VicePresidentRef) getORADataFactory().create(
                    o.toDatum(null), OracleTypes.REF);
        } catch (Exception exn) {
            throw new SQLException("Unable to convert "
                    + o.getClass().getName() + " to VicePresidentRef: "
                    + exn.toString());
        }
    }

    public VicePresident getValue() throws SQLException {
        return (VicePresident) VicePresident.getORADataFactory().create(
                _ref.getSTRUCT(), OracleTypes.REF);
    }

    public void setValue(VicePresident c) throws SQLException {
        _ref.setValue(c.toDatum(_ref.getJavaSqlConnection()));
    }
}
