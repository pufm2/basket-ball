package puf.m2.basket.db.entity;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.REF;
import oracle.sql.STRUCT;

public class DbTeamManyRef implements ORAData, ORADataFactory {
    public static final String _SQL_BASETYPE = "BASKET_USER.T_TEAM_MANY";
    public static final int _SQL_TYPECODE = OracleTypes.REF;

    REF _ref;

    private static final DbTeamManyRef _DbTeamManyRefFactory = new DbTeamManyRef();

    public static ORADataFactory getORADataFactory() {
        return _DbTeamManyRefFactory;
    }

    /* constructor */
    public DbTeamManyRef() {
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
        DbTeamManyRef r = new DbTeamManyRef();
        r._ref = (REF) d;
        return r;
    }

    public static DbTeamManyRef cast(ORAData o) throws SQLException {
        if (o == null)
            return null;
        try {
            return (DbTeamManyRef) getORADataFactory().create(o.toDatum(null),
                    OracleTypes.REF);
        } catch (Exception exn) {
            throw new SQLException("Unable to convert "
                    + o.getClass().getName() + " to DbTeamManyRef: "
                    + exn.toString());
        }
    }

    public DbTeamMany getValue() throws SQLException {
        return (DbTeamMany) DbTeamMany.getORADataFactory().create(
                _ref.getSTRUCT(), OracleTypes.REF);
    }

    public void setValue(DbTeamMany c) throws SQLException {
        _ref.setValue(c.toDatum(_ref.getJavaSqlConnection()));
    }
}
