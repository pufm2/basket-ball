package puf.m2.basket.db.entity;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.REF;
import puf.m2.basket.model.entity.Person;

public class PersonRef implements ORAData, ORADataFactory {
    public static final String _SQL_BASETYPE = "BASKET_USER.T_PERSON";
    public static final int _SQL_TYPECODE = OracleTypes.REF;

    REF _ref;

    private static final PersonRef _PersonRefFactory = new PersonRef();

    public static ORADataFactory getORADataFactory() {
        return _PersonRefFactory;
    }

    /* constructor */
    public PersonRef() {
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
        PersonRef r = new PersonRef();
        r._ref = (REF) d;
        return r;
    }

    public static PersonRef cast(ORAData o) throws SQLException {
        if (o == null)
            return null;
        try {
            return (PersonRef) getORADataFactory().create(o.toDatum(null),
                    OracleTypes.REF);
        } catch (Exception exn) {
            throw new SQLException("Unable to convert "
                    + o.getClass().getName() + " to PersonRef: "
                    + exn.toString());
        }
    }

    public Person getValue() throws SQLException {
        return (Person) Person.getORADataFactory().create(_ref.getSTRUCT(),
                OracleTypes.REF);
    }

    public void setValue(Person c) throws SQLException {
        _ref.setValue(c.toDatum(_ref.getJavaSqlConnection()));
    }
}
