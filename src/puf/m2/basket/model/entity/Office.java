/*@lineinfo:filename=Office*//*@lineinfo:user-code*//*@lineinfo:1^1*/package puf.m2.basket.model.entity;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import puf.m2.basket.db.entity.DbLocation;
import puf.m2.basket.db.entity.DbOffice;
import puf.m2.basket.db.entity.SdoGeometry;
import puf.m2.basket.model.entity.ref.OfficeRef;
import sqlj.runtime.ref.DefaultContext;

public class Office extends DbOffice implements ORAData, ORADataFactory {

    public static final String TABLE_NAME = "office";
    private static final Office _OfficeFactory = new Office();
    
    //private OfficeRef ref;

    public static ORADataFactory getORADataFactory() {
        return _OfficeFactory;
    }

    public Office() {
        super();
    }

    public Office(Connection conn) throws SQLException {
        super(conn);
    }

    public Office(DefaultContext ctx) throws SQLException {
        super(ctx);
    }

    public Office(Integer id, SdoGeometry loc, String officeName,
            Address officeAddress, Integer deleted) throws SQLException {
        setId(id);
        setLoc(loc);
        setOfficeName(officeName);
        setOfficeAddress(officeAddress);
        setDeleted(deleted);
    }

    /* ORAData interface */
    @Override
    protected ORAData createExact(Datum d, int sqlType) throws SQLException {
        return create(new Office(), d, sqlType);
    }

    /* superclass methods */
    @Override
    public java.math.BigDecimal distance(DbLocation locobj)
            throws java.sql.SQLException {

        java.math.BigDecimal __jRt_0 = null;
        try {
            __jRt_0 = super.distance(locobj);

        } catch (java.sql.SQLException except) {
            except.printStackTrace();
            throw new java.sql.SQLException(except.getClass().getName() + ": "
                    + except.getMessage());
        }
        return __jRt_0;
    }
    
    public String toString(){
    	String result = "";
    	try {
			result = getOfficeName();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return result;
    }

}/* @lineinfo:generated-code */