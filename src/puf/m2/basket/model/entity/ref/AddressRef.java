package puf.m2.basket.model.entity.ref;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.REF;
import oracle.sql.STRUCT;
import puf.m2.basket.model.entity.Address;

public class AddressRef implements ORAData, ORADataFactory {
    public static final String _SQL_BASETYPE = "BASKET_USER.T_ADDRESS";
    public static final int _SQL_TYPECODE = OracleTypes.REF;

    REF _ref;

    private static final AddressRef _AddressRefFactory = new AddressRef();

    public static ORADataFactory getORADataFactory() {
        return _AddressRefFactory;
    }

    /* constructor */
    public AddressRef() {
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
        AddressRef r = new AddressRef();
        r._ref = (REF) d;
        return r;
    }

    public static AddressRef cast(ORAData o) throws SQLException {
        if (o == null)
            return null;
        try {
            return (AddressRef) getORADataFactory().create(o.toDatum(null),
                    OracleTypes.REF);
        } catch (Exception exn) {
            throw new SQLException("Unable to convert "
                    + o.getClass().getName() + " to AddressRef: "
                    + exn.toString());
        }
    }

    public Address getValue() throws SQLException {
        return (Address) Address.getORADataFactory().create(_ref.getSTRUCT(),
                OracleTypes.REF);
    }

    public void setValue(Address c) throws SQLException {
        _ref.setValue(c.toDatum(_ref.getJavaSqlConnection()));
    }
}
