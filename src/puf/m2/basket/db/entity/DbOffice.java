/*@lineinfo:filename=DbOffice*//*@lineinfo:user-code*//*@lineinfo:1^1*/package puf.m2.basket.db.entity;

import java.sql.Connection;
import java.sql.SQLException;

import oracle.jdbc.OracleTypes;
import oracle.jpub.runtime.MutableStruct;
import oracle.sql.Datum;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.STRUCT;
import puf.m2.basket.model.entity.Address;
import puf.m2.basket.model.entity.Office;
import puf.m2.basket.model.support.DbProp;
import sqlj.runtime.ref.DefaultContext;

public class DbOffice extends DbLocation implements ORAData, ORADataFactory {
    public static final String _SQL_NAME = "BASKET_USER.T_OFFICE";
    public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

    protected static int[] _sqlType = { 4, 2002, 12, 2002, 4 };
    protected static ORADataFactory[] _factory = new ORADataFactory[5];
    static {
        _factory[1] = SdoGeometry.getORADataFactory();
        _factory[3] = Address.getORADataFactory();
    }
    protected static final DbOffice _DbOfficeFactory = new DbOffice();

    public static ORADataFactory getORADataFactory() {
        return _DbOfficeFactory;
    }

    static {
        _map.put("BASKET_USER.T_OFFICE", _DbOfficeFactory);
    }

    /* constructors */
    protected void _init_struct(boolean init) {
        if (init)
            _struct = new MutableStruct(new Object[5], _sqlType, _factory);
    }

    public DbOffice() {
        _init_struct(true);
        __tx = DefaultContext.getDefaultContext();
    }

    public DbOffice(DefaultContext c) /* throws SQLException */
    {
        _init_struct(true);
        __tx = c;
    }

    public DbOffice(Connection c) /* throws SQLException */
    {
        _init_struct(true);
        __onn = c;
    }

    public DbOffice(Integer id, SdoGeometry loc, String officeName,
            Address officeAddress, Integer deleted) throws SQLException {
        _init_struct(true);
        setId(id);
        setLoc(loc);
        setOfficeName(officeName);
        setOfficeAddress(officeAddress);
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

    public void setFrom(DbOffice o) throws SQLException {
        setContextFrom(o);
        setValueFrom(o);
    }

    protected void setContextFrom(DbOffice o) throws SQLException {
        release();
        __tx = o.__tx;
        __onn = o.__onn;
    }

    protected void setValueFrom(DbOffice o) {
        _struct = o._struct;
    }

    protected ORAData create(DbOffice o, Datum d, int sqlType)
            throws SQLException {
        if (d == null) {
            if (o != null) {
                o.release();
            }
            ;
            return null;
        }
        if (o == null)
            o = new Office();
        o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
        o.__onn = ((STRUCT) d).getJavaSqlConnection();
        return o;
    }

    protected ORAData createExact(Datum d, int sqlType) throws SQLException {
        return create(null, d, sqlType);
    }

    /* accessor methods */
    @DbProp
    public String getOfficeName() throws SQLException {
        return (String) _struct.getAttribute(2);
    }

    public void setOfficeName(String officeName) throws SQLException {
        _struct.setAttribute(2, officeName);
    }

    @DbProp
    public Address getOfficeAddress() throws SQLException {
        return (Address) _struct.getAttribute(3);
    }

    public void setOfficeAddress(Address officeAddress) throws SQLException {
        _struct.setAttribute(3, officeAddress);
    }

    @DbProp
    public Integer getDeleted() throws SQLException {
        return (Integer) _struct.getAttribute(4);
    }

    public void setDeleted(Integer deleted) throws SQLException {
        _struct.setAttribute(4, deleted);
    }

    public java.math.BigDecimal distance(DbLocation LOCOBJ)
            throws java.sql.SQLException {
        DbLocation __jPt_temp = (DbLocation) this;
        java.math.BigDecimal __jPt_result;
        try {
            /* @lineinfo:generated-code *//* @lineinfo:109^5 */

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
                            "0puf.m2.basket.db.entity.DbOffice", theSqlTS);
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

            /* @lineinfo:user-code *//* @lineinfo:114^5 */
        } catch (java.sql.SQLException _err) {
            try {
                getConnectionContext().getExecutionContext().close();
                closeConnection();
                if (__dataSource == null)
                    throw _err;
                /* @lineinfo:generated-code *//* @lineinfo:120^5 */

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
                        __sJT_st = __sJT_ec.prepareOracleCall(__sJT_cc,
                                "1puf.m2.basket.db.entity.DbOffice", theSqlTS);
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

                /* @lineinfo:user-code *//* @lineinfo:125^5 */
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