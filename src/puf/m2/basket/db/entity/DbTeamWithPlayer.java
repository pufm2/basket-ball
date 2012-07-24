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

public class DbTeamWithPlayer implements ORAData, ORADataFactory {
    public static final String _SQL_NAME = "BASKET_USER.T_TEAM_WITH_PLAYER";
    public static final int _SQL_TYPECODE = OracleTypes.ARRAY;

    MutableArray _array;

    private static final DbTeamWithPlayer _DbTeamWithPlayerFactory = new DbTeamWithPlayer();

    public static ORADataFactory getORADataFactory() {
        return _DbTeamWithPlayerFactory;
    }

    /* constructors */
    public DbTeamWithPlayer() {
        this((DbTeamMany[]) null);
    }

    public DbTeamWithPlayer(DbTeamMany[] a) {
        _array = new MutableArray(2002, a, DbTeamMany.getORADataFactory());
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
        DbTeamWithPlayer a = new DbTeamWithPlayer();
        a._array = new MutableArray(2002, (ARRAY) d,
                DbTeamMany.getORADataFactory());
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
    public DbTeamMany[] getArray() throws SQLException {
        return (DbTeamMany[]) _array.getObjectArray(new DbTeamMany[_array
                .length()]);
    }

    public DbTeamMany[] getArray(long index, int count) throws SQLException {
        return (DbTeamMany[]) _array.getObjectArray(index,
                new DbTeamMany[_array.sliceLength(index, count)]);
    }

    public void setArray(DbTeamMany[] a) throws SQLException {
        _array.setObjectArray(a);
    }

    public void setArray(DbTeamMany[] a, long index) throws SQLException {
        _array.setObjectArray(a, index);
    }

    public DbTeamMany getElement(long index) throws SQLException {
        return (DbTeamMany) _array.getObjectElement(index);
    }

    public void setElement(DbTeamMany a, long index) throws SQLException {
        _array.setObjectElement(a, index);
    }

}
