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
import puf.m2.basket.model.entity.Team;

public class DbTeams implements ORAData, ORADataFactory {
    public static final String _SQL_NAME = "BASKET_USER.T_TEAMS";
    public static final int _SQL_TYPECODE = OracleTypes.ARRAY;

    MutableArray _array;

    private static final DbTeams _DbTeamsFactory = new DbTeams();

    public static ORADataFactory getORADataFactory() {
        return _DbTeamsFactory;
    }

    /* constructors */
    public DbTeams() {
        this((Team[]) null);
    }

    public DbTeams(Team[] a) {
        _array = new MutableArray(2002, a, Team.getORADataFactory());
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
        DbTeams a = new DbTeams();
        a._array = new MutableArray(2002, (ARRAY) d, Team.getORADataFactory());
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
    public Team[] getArray() throws SQLException {
        return (Team[]) _array.getObjectArray(new Team[_array.length()]);
    }

    public String[] getArray(long index, int count) throws SQLException {
        return (String[]) _array.getObjectArray(index,
                new String[_array.sliceLength(index, count)]);
    }

    public void setArray(String[] a) throws SQLException {
        _array.setObjectArray(a);
    }

    public void setArray(String[] a, long index) throws SQLException {
        _array.setObjectArray(a, index);
    }

    public String getElement(long index) throws SQLException {
        return (String) _array.getObjectElement(index);
    }

    public void setElement(String a, long index) throws SQLException {
        _array.setObjectElement(a, index);
    }

}
