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
import puf.m2.basket.model.entity.ScoreDetail;

public class DbScoreDetails implements ORAData, ORADataFactory {
    public static final String _SQL_NAME = "BASKET_USER.T_SCOREDETAILS";
    public static final int _SQL_TYPECODE = OracleTypes.ARRAY;

    MutableArray _array;

    private static final DbScoreDetails _DbScoreDetailsFactory = new DbScoreDetails();

    public static ORADataFactory getORADataFactory() {
        return _DbScoreDetailsFactory;
    }

    /* constructors */
    public DbScoreDetails() {
        this((ScoreDetail[]) null);
    }

    public DbScoreDetails(ScoreDetail[] a) {
        _array = new MutableArray(2002, a, ScoreDetail.getORADataFactory());
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
        DbScoreDetails a = new DbScoreDetails();
        a._array = new MutableArray(2002, (ARRAY) d,
                ScoreDetail.getORADataFactory());
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
    public ScoreDetail[] getArray() throws SQLException {
        return (ScoreDetail[]) _array.getObjectArray(new ScoreDetail[_array
                .length()]);
    }

    public ScoreDetail[] getArray(long index, int count) throws SQLException {
        return (ScoreDetail[]) _array.getObjectArray(index,
                new ScoreDetail[_array.sliceLength(index, count)]);
    }

    public void setArray(ScoreDetail[] a) throws SQLException {
        _array.setObjectArray(a);
    }

    public void setArray(ScoreDetail[] a, long index) throws SQLException {
        _array.setObjectArray(a, index);
    }

    public ScoreDetail getElement(long index) throws SQLException {
        return (ScoreDetail) _array.getObjectElement(index);
    }

    public void setElement(ScoreDetail a, long index) throws SQLException {
        _array.setObjectElement(a, index);
    }

}
