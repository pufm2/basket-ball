package puf.m2.basket.db.entity;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;
import oracle.jpub.runtime.MutableArray;
import puf.m2.basket.model.entity.Category;

public class DbCategories implements ORAData, ORADataFactory {
    public static final String _SQL_NAME = "BASKET_USER.T_CATEGORIES";
    public static final int _SQL_TYPECODE = OracleTypes.ARRAY;

    MutableArray _array;

    private static final DbCategories _DbCategoriesFactory = new DbCategories();

    public static ORADataFactory getORADataFactory() {
        return _DbCategoriesFactory;
    }

    /* constructors */
    public DbCategories() {
        this((Category[]) null);
    }

    public DbCategories(Category[] a) {
        _array = new MutableArray(2002, a, Category.getORADataFactory());
    }

    /* ORAData interface */
    @Override
    public Datum toDatum(Connection c) throws SQLException {
        return _array.toDatum(c, _SQL_NAME);
    }

    /* ORADataFactory interface */
    @Override
    public ORAData create(Datum d, int sqlType) throws SQLException {
        if (d == null)
            return null;
        DbCategories a = new DbCategories();
        a._array = new MutableArray(2002, (ARRAY) d,
                Category.getORADataFactory());
        return a;
    }

    public int length() throws SQLException {
        return _array.length();
    }

    public int getBaseType() throws SQLException {
        return _array.getBaseType();
    }

    public String getBaseTypeName() throws SQLException {
        return _array.getBaseTypeName();
    }

    public ArrayDescriptor getDescriptor() throws SQLException {
        return _array.getDescriptor();
    }

    /* array accessor methods */
    public Category[] getArray() throws SQLException {
        return (Category[]) _array
                .getObjectArray(new Category[_array.length()]);
    }

    public Category[] getArray(long index, int count) throws SQLException {
        return (Category[]) _array.getObjectArray(index,
                new Category[_array.sliceLength(index, count)]);
    }

    public void setArray(Category[] a) throws SQLException {
        _array.setObjectArray(a);
    }

    public void setArray(Category[] a, long index) throws SQLException {
        _array.setObjectArray(a, index);
    }

    public Category getElement(long index) throws SQLException {
        return (Category) _array.getObjectElement(index);
    }

    public void setElement(Category a, long index) throws SQLException {
        _array.setObjectElement(a, index);
    }

}
