package puf.m2.basket.db.entity;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.REF;
import puf.m2.basket.model.entity.Category;

public class CategoryRef implements ORAData, ORADataFactory {
    public static final String _SQL_BASETYPE = "BASKET_USER.T_CATEGORY";
    public static final int _SQL_TYPECODE = OracleTypes.REF;

    REF _ref;

    private static final CategoryRef _CategoryRefFactory = new CategoryRef();

    public static ORADataFactory getORADataFactory() {
        return _CategoryRefFactory;
    }

    /* constructor */
    public CategoryRef() {
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
        CategoryRef r = new CategoryRef();
        r._ref = (REF) d;
        return r;
    }

    public static CategoryRef cast(ORAData o) throws SQLException {
        if (o == null)
            return null;
        try {
            return (CategoryRef) getORADataFactory().create(o.toDatum(null),
                    OracleTypes.REF);
        } catch (Exception exn) {
            throw new SQLException("Unable to convert "
                    + o.getClass().getName() + " to CategoryRef: "
                    + exn.toString());
        }
    }

    public Category getValue() throws SQLException {
        return (Category) Category.getORADataFactory().create(_ref.getSTRUCT(),
                OracleTypes.REF);
    }

    public void setValue(Category c) throws SQLException {
        _ref.setValue(c.toDatum(_ref.getJavaSqlConnection()));
    }
}
