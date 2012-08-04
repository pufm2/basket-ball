/*@lineinfo:filename=DbLocation*//*@lineinfo:user-code*//*@lineinfo:1^1*/package puf.m2.basket.db.entity;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.STRUCT;
import oracle.jpub.runtime.MutableStruct;
import puf.m2.basket.model.entity.Office;
import puf.m2.basket.model.support.DbProp;
import sqlj.runtime.ref.DefaultContext;
import sqlj.runtime.ConnectionContext;

public class DbLocation extends Entity implements ORAData, ORADataFactory {
    public static final String _SQL_NAME = "BASKET_USER.T_LOCATION";
    public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

    /* connection management */
    protected Connection __onn = null;
    protected javax.sql.DataSource __dataSource = null;

    public void setDataSource(javax.sql.DataSource dataSource)
            throws SQLException {
        release();
        __dataSource = dataSource;
    }

    public void setDataSourceLocation(String dataSourceLocation)
            throws SQLException {
        javax.sql.DataSource dataSource;
        try {
            Class cls = Class.forName("javax.naming.InitialContext");
            Object ctx = cls.newInstance();
            java.lang.reflect.Method meth = cls.getMethod("lookup",
                    new Class[] { String.class });
            dataSource = (javax.sql.DataSource) meth.invoke(ctx,
                    new Object[] { "java:comp/env/" + dataSourceLocation });
            setDataSource(dataSource);
        } catch (Exception e) {
            throw new java.sql.SQLException("Error initializing DataSource at "
                    + dataSourceLocation + ": " + e.getMessage());
        }
    }

    public Connection getConnection() throws SQLException {
        if (__onn != null)
            return __onn;
        else if (__tx != null)
            return __tx.getConnection();
        else if (__dataSource != null)
            __onn = __dataSource.getConnection();
        return __onn;
    }

    public void release() throws SQLException {
        if (__tx != null && __onn != null)
            __tx.close(ConnectionContext.KEEP_CONNECTION);
        __onn = null;
        __tx = null;
        __dataSource = null;
    }

    public void closeConnection() {
        if (__dataSource != null) {
            try {
                if (__onn != null) {
                    __onn.close();
                }
            } catch (java.sql.SQLException e) {
            }
            try {
                if (__tx != null) {
                    __tx.close();
                }
            } catch (java.sql.SQLException e) {
            }
            __onn = null;
            __tx = null;
        }
    }

    protected DefaultContext __tx = null;

    public void setConnectionContext(DefaultContext ctx) throws SQLException {
        release();
        __tx = ctx;
    }

    public DefaultContext getConnectionContext() throws SQLException {
        if (__tx == null) {
            __tx = (getConnection() == null) ? DefaultContext
                    .getDefaultContext() : new DefaultContext(getConnection());
        }
        return __tx;
    };

    protected MutableStruct _struct;

    protected static int[] _sqlType = { 4, 2002, 4 };
    protected static ORADataFactory[] _factory = new ORADataFactory[3];
    static {
        _factory[1] = SdoGeometry.getORADataFactory();
    }
    protected static final DbLocation _DbLocationFactory = new DbLocation();

    public static ORADataFactory getORADataFactory() {
        return _DbLocationFactory;
    }

    protected static java.util.Hashtable _map = new java.util.Hashtable();
    protected static boolean _initialized = false;

    protected static synchronized void init() {
        if (!_initialized) {
            _initialized = true;
            _map.put("BASKET_USER.T_LOCATION",
                    puf.m2.basket.db.entity.DbLocation.getORADataFactory());
            _map.put("BASKET_USER.T_OFFICE",
                    Office.getORADataFactory());
        }
    }

    /* constructors */
    protected void _init_struct(boolean init) {
        if (init)
            _struct = new MutableStruct(new Object[3], _sqlType, _factory);
    }

    public DbLocation() {
        _init_struct(true);
        __tx = DefaultContext.getDefaultContext();
    }

    public DbLocation(DefaultContext c) /* throws SQLException */
    {
        _init_struct(true);
        __tx = c;
    }

    public DbLocation(Connection c) /* throws SQLException */
    {
        _init_struct(true);
        __onn = c;
    }

    public DbLocation(Integer id, SdoGeometry loc, Integer deleted)
            throws SQLException {
        _init_struct(true);
        setId(id);
        setLoc(loc);
        setDeleted(deleted);
    }

    /* ORAData interface */
    public Datum toDatum(Connection c) throws SQLException {
        if (__tx != null && __onn != c)
            release();
        __onn = c;
        return _struct.toDatum(c, _SQL_NAME);
    }

    /* ORADataFactory interface */
    public ORAData create(Datum d, int sqlType) throws SQLException {
        return create(null, d, sqlType);
    }

    public void setFrom(DbLocation o) throws SQLException {
        setContextFrom(o);
        setValueFrom(o);
    }

    protected void setContextFrom(DbLocation o) throws SQLException {
        release();
        __tx = o.__tx;
        __onn = o.__onn;
    }

    protected void setValueFrom(DbLocation o) {
        _struct = o._struct;
    }

    protected ORAData create(DbLocation o, Datum d, int sqlType)
            throws SQLException {
        if (d == null) {
            if (o != null) {
                o.release();
            }
            ;
            return null;
        }
        if (o == null)
            return createFromFactory("DbLocation", d, sqlType);
        o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
        o.__onn = ((STRUCT) d).getJavaSqlConnection();
        return o;
    }

    protected ORAData createExact(Datum d, int sqlType) throws SQLException {
        DbLocation o = new DbLocation();
        o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
        o.__onn = ((STRUCT) d).getJavaSqlConnection();
        return o;
    }

    protected ORAData createFromFactory(String s, Datum d, int sqlType)
            throws SQLException {
        String sql = ((STRUCT) d).getSQLTypeName();
        init();
        DbLocation factory = (DbLocation) _map.get(sql);
        if (factory == null) {
            int p;
            if ((p = sql.indexOf(".")) >= 0) {
                factory = (DbLocation) _map.get(sql.substring(p + 1));
                if (factory != null)
                    _map.put(sql, factory);
            }
            if (factory == null)
                throw new SQLException("Unable to convert a " + sql + " to a "
                        + s + " or a subclass of " + s);
        }
        return factory.createExact(d, sqlType);
    }

    /* accessor methods */
    @DbProp
    public Integer getId() throws SQLException {
        return (Integer) _struct.getAttribute(0);
    }

    public void setId(Integer id) throws SQLException {
        _struct.setAttribute(0, id);
    }

    @DbProp
    public SdoGeometry getLoc() throws SQLException {
        return (SdoGeometry) _struct.getAttribute(1);
    }

    public void setLoc(SdoGeometry loc) throws SQLException {
        _struct.setAttribute(1, loc);
    }

    @DbProp
    public Integer getDeleted() throws SQLException {
        return (Integer) _struct.getAttribute(2);
    }

    public void setDeleted(Integer deleted) throws SQLException {
        _struct.setAttribute(2, deleted);
    }

    public java.math.BigDecimal distance(DbLocation LOCOBJ)
            throws java.sql.SQLException {
        DbLocation __jPt_temp = (DbLocation) this;
        java.math.BigDecimal __jPt_result;
        try {
            /* @lineinfo:generated-code *//* @lineinfo:181^5 */

            // ************************************************************
            // #sql [getConnectionContext()] { BEGIN
            // :__jPt_result := :__jPt_temp.DISTANCE(
            // :LOCOBJ);
            // END;
            // };
            // ************************************************************

            {
                // declare temps
                oracle.jdbc.OracleCallableStatement __sJT_st = null;
                sqlj.runtime.ref.DefaultContext __sJT_cc = getConnectionContext();
                if (__sJT_cc == null)
                    sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
                sqlj.runtime.ExecutionContext.OracleContext __sJT_ec = ((__sJT_cc
                        .getExecutionContext() == null) ? sqlj.runtime.ExecutionContext
                        .raiseNullExecCtx() : __sJT_cc.getExecutionContext()
                        .getOracleContext());
                try {
                    String theSqlTS = "BEGIN\n       :1   :=  :2  .DISTANCE(\n       :3  );\n      END;";
                    __sJT_st = __sJT_ec.prepareOracleCall(__sJT_cc,
                            "0puf.m2.basket.db.entity.DbLocation", theSqlTS);
                    if (__sJT_ec.isNew()) {
                        __sJT_st.registerOutParameter(1,
                                oracle.jdbc.OracleTypes.NUMERIC);
                    }
                    // set IN parameters
                    if (__jPt_temp == null)
                        __sJT_st.setNull(2, 2002, "BASKET_USER.T_LOCATION");
                    else
                        __sJT_st.setORAData(2, __jPt_temp);
                    if (LOCOBJ == null)
                        __sJT_st.setNull(3, 2002, "BASKET_USER.T_LOCATION");
                    else
                        __sJT_st.setORAData(3, LOCOBJ);
                    // execute statement
                    __sJT_ec.oracleExecuteUpdate();
                    // retrieve OUT parameters
                    __jPt_result = __sJT_st.getBigDecimal(1);
                } finally {
                    __sJT_ec.oracleClose();
                }
            }

            // ************************************************************

            /* @lineinfo:user-code *//* @lineinfo:186^5 */
        } catch (java.sql.SQLException _err) {
            try {
                getConnectionContext().getExecutionContext().close();
                closeConnection();
                if (__dataSource == null)
                    throw _err;
                /* @lineinfo:generated-code *//* @lineinfo:192^5 */

                // ************************************************************
                // #sql [getConnectionContext()] { BEGIN
                // :__jPt_result := :__jPt_temp.DISTANCE(
                // :LOCOBJ);
                // END;
                // };
                // ************************************************************

                {
                    // declare temps
                    oracle.jdbc.OracleCallableStatement __sJT_st = null;
                    sqlj.runtime.ref.DefaultContext __sJT_cc = getConnectionContext();
                    if (__sJT_cc == null)
                        sqlj.runtime.error.RuntimeRefErrors
                                .raise_NULL_CONN_CTX();
                    sqlj.runtime.ExecutionContext.OracleContext __sJT_ec = ((__sJT_cc
                            .getExecutionContext() == null) ? sqlj.runtime.ExecutionContext
                            .raiseNullExecCtx() : __sJT_cc
                            .getExecutionContext().getOracleContext());
                    try {
                        String theSqlTS = "BEGIN\n       :1   :=  :2  .DISTANCE(\n       :3  );\n      END;";
                        __sJT_st = __sJT_ec
                                .prepareOracleCall(__sJT_cc,
                                        "1puf.m2.basket.db.entity.DbLocation",
                                        theSqlTS);
                        if (__sJT_ec.isNew()) {
                            __sJT_st.registerOutParameter(1,
                                    oracle.jdbc.OracleTypes.NUMERIC);
                        }
                        // set IN parameters
                        if (__jPt_temp == null)
                            __sJT_st.setNull(2, 2002, "BASKET_USER.T_LOCATION");
                        else
                            __sJT_st.setORAData(2, __jPt_temp);
                        if (LOCOBJ == null)
                            __sJT_st.setNull(3, 2002, "BASKET_USER.T_LOCATION");
                        else
                            __sJT_st.setORAData(3, LOCOBJ);
                        // execute statement
                        __sJT_ec.oracleExecuteUpdate();
                        // retrieve OUT parameters
                        __jPt_result = __sJT_st.getBigDecimal(1);
                    } finally {
                        __sJT_ec.oracleClose();
                    }
                }

                // ************************************************************

                /* @lineinfo:user-code *//* @lineinfo:197^5 */
            } catch (java.sql.SQLException _err2) {
                try {
                    getConnectionContext().getExecutionContext().close();
                } catch (java.sql.SQLException _sqle) {
                }
                throw _err;
            }
        }
        return __jPt_result;
    }
}/* @lineinfo:generated-code */