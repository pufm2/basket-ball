package puf.m2.basket.db.entity;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.STRUCT;
import oracle.jpub.runtime.MutableStruct;
import puf.m2.basket.model.entity.Category;
import puf.m2.basket.model.support.DbProp;

public class DbCategory extends Entity implements ORAData, ORADataFactory {
    public static final String _SQL_NAME = "BASKET_USER.T_CATEGORY";
    public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

    protected MutableStruct _struct;

    protected static int[] _sqlType = { 4, 12, 2003, 4 };
    protected static ORADataFactory[] _factory = new ORADataFactory[4];
    static {
        _factory[2] = DbTeams.getORADataFactory();
    }
    protected static final DbCategory _DbCategoryFactory = new DbCategory();

    public static ORADataFactory getORADataFactory() {
        return _DbCategoryFactory;
    }

    /* constructors */
    protected void _init_struct(boolean init) {
        if (init)
            _struct = new MutableStruct(new Object[4], _sqlType, _factory);
    }

    public DbCategory() {
        _init_struct(true);
    }

    public DbCategory(Integer id, String categoryName, DbTeams listteam,
            Integer deleted) throws SQLException {
        _init_struct(true);
        setId(id);
        setCategoryName(categoryName);
        setListteam(listteam);
        setDeleted(deleted);
    }

    /* ORAData interface */
    @Override
    public Datum toDatum(Connection c) throws SQLException {
        return _struct.toDatum(c, _SQL_NAME);
    }

    /* ORADataFactory interface */
    @Override
    public ORAData create(Datum d, int sqlType) throws SQLException {
        return create(null, d, sqlType);
    }

    protected ORAData create(DbCategory o, Datum d, int sqlType)
            throws SQLException {
        if (d == null)
            return null;
        if (o == null)
            o = new Category();
        o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
        return o;
    }

    /* accessor methods */
    @Override
    @DbProp
    public Integer getId() throws SQLException {
        return (Integer) _struct.getAttribute(0);
    }

    public void setId(Integer id) throws SQLException {
        _struct.setAttribute(0, id);
    }

    @DbProp
    public String getCategoryName() throws SQLException {
        return (String) _struct.getAttribute(1);
    }

    public void setCategoryName(String categoryName) throws SQLException {
        _struct.setAttribute(1, categoryName);
    }

    @DbProp
    public DbTeams getListteam() throws SQLException {
        return (DbTeams) _struct.getAttribute(2);
    }

    public void setListteam(DbTeams listteam) throws SQLException {
        _struct.setAttribute(2, listteam);
    }

    @DbProp
    public Integer getDeleted() throws SQLException {
        return (Integer) _struct.getAttribute(3);
    }

    public void setDeleted(Integer deleted) throws SQLException {
        _struct.setAttribute(3, deleted);
    }

}
