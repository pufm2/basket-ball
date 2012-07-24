package puf.m2.basket.db.entity;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.REF;
import puf.m2.basket.model.entity.User;

public class UserRef implements ORAData, ORADataFactory {
    public static final String _SQL_BASETYPE = "BASKET_USER.T_USER";
    public static final int _SQL_TYPECODE = OracleTypes.REF;

    REF _ref;

    private static final UserRef _UserRefFactory = new UserRef();

    public static ORADataFactory getORADataFactory() {
        return _UserRefFactory;
    }

    /* constructor */
    public UserRef() {
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
        UserRef r = new UserRef();
        r._ref = (REF) d;
        return r;
    }

    public static UserRef cast(ORAData o) throws SQLException {
        if (o == null)
            return null;
        try {
            return (UserRef) getORADataFactory().create(o.toDatum(null),
                    OracleTypes.REF);
        } catch (Exception exn) {
            throw new SQLException("Unable to convert "
                    + o.getClass().getName() + " to UserRef: " + exn.toString());
        }
    }

    public User getValue() throws SQLException {
        return (User) User.getORADataFactory().create(_ref.getSTRUCT(),
                OracleTypes.REF);
    }

    public void setValue(User c) throws SQLException {
        _ref.setValue(c.toDatum(_ref.getJavaSqlConnection()));
    }
}
